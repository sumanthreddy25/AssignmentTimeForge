package com.Assignment.restdemo.controller;

import com.Assignment.restdemo.WeatherProviderFactory.WeatherProviderFactory;
import com.Assignment.restdemo.exception.ProviderNotAvailableException;
import com.Assignment.restdemo.service.IWeatherService;
import com.Assignment.restdemo.service.TomorrowDocsApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

}
