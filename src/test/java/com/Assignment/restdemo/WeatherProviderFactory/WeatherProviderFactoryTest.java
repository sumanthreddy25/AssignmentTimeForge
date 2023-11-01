package com.Assignment.restdemo.WeatherProviderFactory;

import com.Assignment.restdemo.WeatherProviderFactory.IWeatherProvider;
import com.Assignment.restdemo.WeatherProviderFactory.WeatherProviderFactory;
import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.service.TomorrowDocsApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherProviderFactoryTest {


    @Test
    public void testGetProviderWithValidServiceType() throws ProviderNotAvailableException {

        TomorrowDocsApiService tomorrowDocsApiService = Mockito.mock(TomorrowDocsApiService.class);
        WeatherProviderFactory weatherProviderFactory = new WeatherProviderFactory();
        weatherProviderFactory.tomorrowDocsApiService = tomorrowDocsApiService;
        IWeatherProvider provider = weatherProviderFactory.getProvider("tomorrowdocs");
        assertNotNull(provider);
    }

    @Test
    public void testGetProviderWithNullServiceType() throws ProviderNotAvailableException {
        TomorrowDocsApiService tomorrowDocsApiService = Mockito.mock(TomorrowDocsApiService.class);
        WeatherProviderFactory weatherProviderFactory = new WeatherProviderFactory();
        weatherProviderFactory.tomorrowDocsApiService = tomorrowDocsApiService;
        IWeatherProvider provider = weatherProviderFactory.getProvider(null);
        assertNotNull(provider);
    }

    @Test
    public void testGetProviderWithInvalidServiceType() {

        WeatherProviderFactory weatherProviderFactory = new WeatherProviderFactory();
        assertThrows(ProviderNotAvailableException.class, () -> {
            weatherProviderFactory.getProvider("unknown");
        });
    }
}