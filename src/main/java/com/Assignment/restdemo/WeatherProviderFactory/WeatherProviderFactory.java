package com.Assignment.restdemo.WeatherProviderFactory;

import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.service.TomorrowDocsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The WeatherProviderFactory class is responsible for providing weather service providers based on the specified service provider name.
 * It acts as a factory to create instances of classes implementing the IWeatherProvider interface, allowing dynamic selection of weather data sources.
 */
@Service
public class WeatherProviderFactory {

    @Autowired
    public TomorrowDocsApiService tomorrowDocsApiService;

    /**
     * Get a weather service provider based on the specified service provider name.
     *
     * @param serviceProvider The name of the weather service provider.
     * @return An instance of a weather provider that implements the IWeatherProvider interface.
     * @throws ProviderNotAvailableException if the specified provider is not available or an invalid provider name is provided.
     */
    public IWeatherProvider getProvider(String serviceProvider) throws ProviderNotAvailableException {
        if (serviceProvider == null || serviceProvider.isEmpty()) {
            // If serviceType is null or empty, use a default value
            serviceProvider = "tomorrowdocs";
        }

        switch (serviceProvider.toLowerCase()) {
            case "tomorrowdocs":
                return tomorrowDocsApiService;
            default:
                throw new ProviderNotAvailableException("This provider is currently not available");
        }
    }
}
