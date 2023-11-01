package com.Assignment.restdemo.model;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherDataTest {
    private WeatherData weatherData = weatherData = new WeatherData();

    @Test
    public void testDefaultConstructor() {
        assertNotNull(weatherData);
        assertEquals(0, weatherData.getId());
        assertEquals(0.0, weatherData.getHighTemperature(), 0.01);
        assertEquals(0.0, weatherData.getLowTemperature(), 0.01);
        assertEquals(0.0, weatherData.getHumidity(), 0.01);
        assertNull(weatherData.getDate());
        assertEquals(0.0, weatherData.getPrecipitation(), 0.01);
        assertEquals(0.0, weatherData.getWindSpeed(), 0.01);
        assertNull(weatherData.getZipCode());
    }

    @Test
    public void testParameterizedConstructor() {
        Date date = new Date();
        WeatherData weatherData = new WeatherData(30.5, 15.3, 70.2, date, 0.5, 12.0, "12345");
        assertNotNull(weatherData);
        assertEquals(30.5, weatherData.getHighTemperature());
        assertEquals(15.3, weatherData.getLowTemperature());
        assertEquals(70.2, weatherData.getHumidity());
        assertEquals(date, weatherData.getDate());
        assertEquals(0.5, weatherData.getPrecipitation());
        assertEquals(12.0, weatherData.getWindSpeed());
        assertEquals("12345", weatherData.getZipCode());
    }

    @Test
    public void testGetSetId() {
        weatherData.setId(1);
        assertEquals(1, weatherData.getId());
    }

    @Test
    public void testGetSetHighTemperature() {
        weatherData.setHighTemperature(30.5);
        assertEquals(30.5, weatherData.getHighTemperature(), 0.01);
    }

    @Test
    public void testGetSetLowTemperature() {
        weatherData.setLowTemperature(15.3);
        assertEquals(15.3, weatherData.getLowTemperature(), 0.01);
    }

    @Test
    public void testGetSetHumidity() {
        weatherData.setHumidity(70.2);
        assertEquals(70.2, weatherData.getHumidity(), 0.01);
    }

    @Test
    public void testGetSetZipCode() {
        weatherData.setZipCode("12345");
        assertEquals("12345", weatherData.getZipCode());
    }

    @Test
    public void testGetSetDate() {
        Date date = new Date();
        weatherData.setDate(date);
        assertEquals(date, weatherData.getDate());
    }

    @Test
    public void testGetSetPrecipitation() {
        weatherData.setPrecipitation(0.5);
        assertEquals(0.5, weatherData.getPrecipitation(), 0.01);
    }

    @Test
    public void testGetSetWindSpeed() {
        weatherData.setWindSpeed(12.0);
        assertEquals(12.0, weatherData.getWindSpeed(), 0.01);
    }
}