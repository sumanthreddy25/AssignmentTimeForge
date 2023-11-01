package com.Assignment.restdemo.service;

import com.Assignment.restdemo.WeatherProviderFactory.IWeatherProvider;
import com.Assignment.restdemo.model.WeatherData;
import com.Assignment.restdemo.repository.WeatherDataRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * The TomorrowDocsApiService class is responsible for retrieving 6 days weather forecast data
 * for a given ZIP code using an external API provided by TomorrowDocs. It implements the
 * IWeatherProvider interface, allowing dynamic weather data retrieval based on the ZIP code.
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TomorrowDocsApiService implements IWeatherProvider {


    private static final Logger logger = LogManager.getLogger(TomorrowDocsApiService.class);


    WeatherDataRepository weatherDataRepository;
    @Value("${tomorrowDocsApi.baseUrl}")
    String baseUrl;
    @Value("${tomorrowDocsApi.appId}")
    String appId;
    private RestTemplate restTemplate;
    @Autowired
    public TomorrowDocsApiService(WeatherDataRepository weatherDataRepository, RestTemplate restTemplate) {
        this.weatherDataRepository = weatherDataRepository;
        this.restTemplate=restTemplate;
    }

    /**
     * Retrieves five days weather forecast data for a given zip code using an external API.
     *
     * @param zipCode The zip code for which weather data is requested.
     * @return An ArrayList of WeatherData objects containing weather information for five days
     */
    @Override
    public ArrayList<WeatherData> getWeatherData(String zipCode) {
        // Constructing the API endpoint URL
        String endPoint = baseUrl + zipCode + "&timesteps=1d&apikey=" + appId;
        logger.info("External API Endpoint: " + endPoint);

        // Initialize RestTemplate to make HTTP requests

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endPoint, String.class);
        logger.info("API Response Status: " + responseEntity.getStatusCode());

        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        List<WeatherData> weatherDataList = new ArrayList<WeatherData>();

        try {
            // Parse the JSON response
            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode timelinesNode = rootNode.get("timelines");

            if (timelinesNode != null) {
                JsonNode dailyArray = timelinesNode.get("daily");

                if (dailyArray != null) {
                    for (JsonNode dayNode : dailyArray) {
                        String time = dayNode.get("time").asText();
                        logger.info("Time is: " + time);
                        // Parsing the date from the timestamp
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date date = null;
                        try {
                            date = sdf.parse(time);
                            logger.info("Parsed Date: " + date);
                        } catch (Exception e) {
                            logger.error("Error parsing date: " + e.getMessage());
                            e.printStackTrace();
                        }

                        // Extracting weather data values
                        double temperatureMin = dayNode.get("values").get("temperatureMin").asDouble();
                        double temperatureMax = dayNode.get("values").get("temperatureMax").asDouble();
                        double humidityAvg = dayNode.get("values").get("humidityAvg").asDouble();
                        double precipitation = dayNode.get("values").get("precipitationProbabilityAvg").asDouble();
                        double windSpeedAvg = dayNode.get("values").get("windSpeedAvg").asDouble();

                        weatherDataList.add(new WeatherData(temperatureMax, temperatureMin, humidityAvg, date, precipitation, windSpeedAvg, zipCode));
                        logger.info("Added weather data: " + weatherDataList);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while processing weather data: " + e.getMessage());
            e.printStackTrace();
        }

        // Save weather data to the repository
        try {
            weatherDataRepository.saveAll(weatherDataList);
            logger.info("Saved weather data to the repository.");
        }
        catch (Exception e)
        {
            logger.error("An error occurred while saving weather data: " + e.getMessage());
            e.printStackTrace();
        }
        return (ArrayList<WeatherData>) weatherDataList;
    }


}
