package com.Assignment.restdemo.controller;

import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.model.WeatherData;
import com.Assignment.restdemo.service.WeatherProvider;
import com.Assignment.restdemo.service.WeatherProviderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;

@RestController
@RequestMapping("/cloudvendor")
public class WeatherController {


    private static final Logger logger = LogManager.getLogger(WeatherController.class);
    private final WeatherProviderFactory weatherProviderFactory;


    @Autowired
    public WeatherController(WeatherProviderFactory weatherProviderFactory) {
        this.weatherProviderFactory = weatherProviderFactory;

    }

    @GetMapping("/forecast/{zipCode}")
    public ResponseEntity<?> getSevenDayForecast(@PathVariable("zipCode") String zipCode, @RequestParam("service") String service)  {
        WeatherProvider provider;
        logger.info("entered zipcode" + zipCode);
        try {
             provider= weatherProviderFactory.getProvider(service);
        }
        catch (ProviderNotAvailableException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

        try {
            ArrayList<WeatherData> weatherDataList = provider.getWeatherData(zipCode);
            return new ResponseEntity<>(weatherDataList, HttpStatus.OK);
        } catch (HttpServerErrorException.GatewayTimeout e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
        } catch (HttpServerErrorException.BadGateway e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}