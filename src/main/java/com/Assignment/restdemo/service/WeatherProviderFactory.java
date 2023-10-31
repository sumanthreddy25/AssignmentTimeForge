package com.Assignment.restdemo.service;

import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherProviderFactory {


    @Autowired
    OpenWeatherMapService openWeatherMapService;

    public WeatherProvider getProvider(String service) throws ProviderNotAvailableException {

		if(service.isEmpty())
		{
			service="openweathermap";
		}

        switch (service.toLowerCase()) {
            case "openweathermap":
                return openWeatherMapService;
            default:
                throw new ProviderNotAvailableException(" This provider is currently not available");
        }
    }
}
