package com.Assignment.restdemo.controller;

import com.Assignment.restdemo.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WeatherController {

    @Autowired
    IWeatherService weatherService;
    /**
     * Retrieves 6 day weather forecast for a given ZIP code using a specified weather service
     *
     * @param zipCode     The ZIP code for which the weather forecast is requested.
     * @param serviceProvider The weather service provider (e.g., "openWeatherApi", "tomorrowDocsApi").
     * @return A ResponseEntity containing the weather forecast data or an error message.
     */
    @GetMapping("forecast/{zipCode}")
    public ResponseEntity<?> getSixDayForecast(@PathVariable("zipCode") String zipCode, @RequestParam(value = "serviceProvider",required = false) String serviceProvider) {
        return weatherService.getWeatherForecast(zipCode, serviceProvider);
    }

}