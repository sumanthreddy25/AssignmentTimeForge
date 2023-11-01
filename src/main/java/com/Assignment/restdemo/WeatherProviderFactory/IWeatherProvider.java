package com.Assignment.restdemo.WeatherProviderFactory;

import com.Assignment.restdemo.model.WeatherData;

import java.util.ArrayList;

public interface IWeatherProvider {
    ArrayList<WeatherData> getWeatherData(String zipCode);
}
