package com.Assignment.restdemo.service;

import com.Assignment.restdemo.model.WeatherData;

import java.util.ArrayList;

public interface WeatherProvider {
    ArrayList<WeatherData> getWeatherData(String zipCode);
}
