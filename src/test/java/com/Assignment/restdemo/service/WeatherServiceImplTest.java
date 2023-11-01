package com.Assignment.restdemo.service;

import com.Assignment.restdemo.WeatherProviderFactory.IWeatherProvider;
import com.Assignment.restdemo.WeatherProviderFactory.WeatherProviderFactory;
import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.service.Impl.WeatherServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherServiceImplTest {


        private WeatherServiceImpl weatherService;
        private WeatherProviderFactory weatherProviderFactory;

        @Before
        public void setUp() {
            // Initialize the WeatherServiceImpl and mock WeatherProviderFactory
            weatherService = new WeatherServiceImpl();
            weatherProviderFactory = mock(WeatherProviderFactory.class);
            weatherService.weatherProviderFactory = weatherProviderFactory;
        }

        @Test
        public void testGetWeatherUpdatesSuccess() throws ProviderNotAvailableException {
            // Mock the WeatherProvider and its response
            IWeatherProvider weatherProvider = mock(IWeatherProvider.class);
            when(weatherProvider.getWeatherData("12345")).thenReturn(new ArrayList<>());

            // Mock the WeatherProviderFactory to return the WeatherProvider
            when(weatherProviderFactory.getProvider("someServiceType")).thenReturn(weatherProvider);

            ResponseEntity<?> response = weatherService.getWeatherForecast("12345", "someServiceType");

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    @Test
    public void testGetWeatherUpdatesInternalServerError() throws ProviderNotAvailableException {

        IWeatherProvider weatherProvider = mock(IWeatherProvider.class);
        when(weatherProvider.getWeatherData("12345")).thenThrow(new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)));

        when(weatherProviderFactory.getProvider("someServiceType")).thenReturn(weatherProvider);

        ResponseEntity<?> response = weatherService.getWeatherForecast("12345", "someServiceType");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    }
