package com.service.forecast.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.forecast.entity.objective.ForecastSummary;
import com.service.forecast.entity.original.ForecastData;
import com.service.forecast.util.OpenWeatherMapClient;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class TestForecastweatherdata {

    private OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient();

    @Before
    public void setUp() throws Exception {
        Log4jUtils.init();
    }

    @Test
    public void testMockData() throws Exception {
        System.out.println("unit test for the forecast mock data");

        ClassPathResource resource = new ClassPathResource("mock/forecast.json");
        InputStream inputStream = resource.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        ForecastData mockData = mapper.readValue(data, ForecastData.class);
        Assert.assertFalse(mockData == null);
        Assert.assertEquals(mockData.getCnt(), 36);
    }


    @Test
    public void testShowApi() throws Exception {
        System.out.println("unit test for show api");

        Field field = OpenWeatherMapClient.class.getDeclaredField("mockEnabled");
        field.setAccessible(true);
        field.set(openWeatherMapClient, true);
        ForecastSummary summary = openWeatherMapClient.showForecastWeather("shenzhen");
        Assert.assertFalse(summary == null);
        Assert.assertEquals(summary.getCityName(), "shenzhen");
    }

}