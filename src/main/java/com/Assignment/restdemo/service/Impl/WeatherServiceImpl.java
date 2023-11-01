package com.Assignment.restdemo.service.Impl;


import com.Assignment.restdemo.WeatherProviderFactory.IWeatherProvider;
import com.Assignment.restdemo.WeatherProviderFactory.WeatherProviderFactory;
import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.model.WeatherData;
import com.Assignment.restdemo.service.IWeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;

/**
 * The WeatherServiceImpl class is responsible for retrieving 6-day weather forecasts for a given ZIP code
 * using a specified weather service provider. It implements the IWeatherService interface, allowing users
 * to request weather updates by specifying the ZIP code and the service provider.
 * It utilizes the WeatherProviderFactory to obtain the appropriate weather service provider instance
 * based on the specified service name.
 */
@Service
public class WeatherServiceImpl implements IWeatherService {
    private static final Logger logger = LogManager.getLogger(WeatherServiceImpl.class);



    @Autowired
    public WeatherProviderFactory weatherProviderFactory;


    /**
     * Retrieves 6 day weather Forecast for a given ZIP code using a specified weather service provider.
     *
     * @param-zipCode       The ZIP code for which weather updates are requested.
     * @param-serviceProvider The name of the weather service provider to use.
     * @return ResponseEntity containing weather data or an error response.
     */
    @Override
    public ResponseEntity<?> getWeatherForecast(String zipCode, String serviceProvider) {

        IWeatherProvider  provider;

        logger.info("entered zipcode" + zipCode);

        try {
              provider = weatherProviderFactory.getProvider(serviceProvider);

            logger.info("Weather provider obtained for service: " + serviceProvider);
        } catch (ProviderNotAvailableException e) {
            logger.error("Provider not available for service: " + serviceProvider, e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        try {
            ArrayList<WeatherData> weatherDataList = provider.getWeatherData(zipCode);

            logger.info("Weather data retrieved for ZIP code: " + zipCode);

            return new ResponseEntity<>(weatherDataList, HttpStatus.OK);
        } catch (
                HttpServerErrorException.GatewayTimeout e) {

            logger.error("Gateway Timeout Error: " + e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
        } catch (HttpServerErrorException.BadGateway e) {

            logger.error("Bad Gateway Error: " + e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            logger.error("Internal Server Error: " + e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
