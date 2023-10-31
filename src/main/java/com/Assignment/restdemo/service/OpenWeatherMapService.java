package com.Assignment.restdemo.service;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherMapService implements WeatherProvider {
    private static final Logger logger = LogManager.getLogger(OpenWeatherMapService.class);
    //private String openWeatherMapApiKey="7fa18767eaab164c34d8b800a6a12872";
    @Autowired
    WeatherDataRepository weatherDataRepository;
    @Value("${openWeatherApp.baseUrl}")
    private String baseUrl;
    @Value("${openWeatherApp.defaultLocation}")
    private String defaultLocation;
    @Value("${openWeatherApp.appId}")
    private String appId;

    @Autowired
    public OpenWeatherMapService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }


    @Override
    public ArrayList<WeatherData> getWeatherData(String zipCode) {



        String endPoint = baseUrl + "/forecast?q=" + zipCode + defaultLocation + "&cnt=7&appid=" + appId;
        logger.info(endPoint);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endPoint, String.class);

        String responseBody = responseEntity.getBody();


        ObjectMapper objectMapper = new ObjectMapper();
        List<WeatherData> weatherDataList = new ArrayList<WeatherData>();

        try {
            JsonNode root = objectMapper.readTree(responseBody);


            JsonNode list = root.get("list");

            for (int i = 0; i < 7; i++) {
                JsonNode item = list.get(i);
                logger.info(item);
                double highTemperature = item.get("main").get("temp_max").asDouble();
                double lowTemperature = item.get("main").get("temp_min").asDouble();
                int humidity = item.get("main").get("humidity").asInt();
                double precipitationPercentage = item.get("pop").asDouble();

                int timeStamp = item.get("dt").asInt();
                Date date = new Date(timeStamp * 1000L);
                String condition = item.get("weather").get(0).get("description").asText();
                weatherDataList.add(new WeatherData(highTemperature, lowTemperature, humidity, condition, zipCode, date, precipitationPercentage));
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        weatherDataRepository.saveAll(weatherDataList);
        return (ArrayList<WeatherData>) weatherDataList;

    }

}
