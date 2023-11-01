package com.Assignment.restdemo.service;

import org.springframework.http.ResponseEntity;

public interface IWeatherService {


    ResponseEntity<?> getWeatherForecast(String zipCode, String serviceProvider);
}
