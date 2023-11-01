package com.Assignment.restdemo.service;

import com.Assignment.restdemo.model.WeatherData;
import com.Assignment.restdemo.repository.WeatherDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TomorrowDocsApiServiceTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TomorrowDocsApiService weatherService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        weatherService = new TomorrowDocsApiService(weatherDataRepository, restTemplate);
    }

    @Test
    public void testGetWeatherData() {
        String zipCode = "12345";
        String mockResponse = "{ \"timelines\": { \"daily\": [{ \"time\": \"2023-11-01T12:00:00Z\", \"values\": { \"temperatureMin\": 20, \"temperatureMax\": 25, \"humidityAvg\": 60, \"precipitationProbabilityAvg\": 0.1, \"windSpeedAvg\": 5.0 } } ] } }";

        // Mocking the RestTemplate's response
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(mockResponseEntity);

        // Test the getWeatherData method
        List<WeatherData> weatherDataList = weatherService.getWeatherData(zipCode);

        // Verify that the WeatherDataRepository's save method is called
        Mockito.verify(weatherDataRepository).saveAll(Mockito.anyList());

        // Verify the weather data
        assertEquals(1, weatherDataList.size());
        WeatherData weatherData = weatherDataList.get(0);
        assertEquals(25.0, weatherData.getHighTemperature());
        assertEquals(20.0, weatherData.getLowTemperature());
        assertEquals(60.0, weatherData.getHumidity());
        assertEquals(0.1, weatherData.getPrecipitation());
        assertEquals(5.0, weatherData.getWindSpeed());
    }
}