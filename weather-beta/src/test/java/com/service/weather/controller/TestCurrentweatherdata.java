package com.service.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.weather.entity.objective.CurrentWeatherSummary;
import com.service.weather.entity.original.UltravioletIndex;
import com.service.weather.entity.original.WeatherData;
import com.service.weather.util.OpenWeatherMapClient;
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

public class TestCurrentweatherdata {

    private OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient();

    @Before
    public void setUp() throws Exception {
        Log4jUtils.init();
    }

    @Test
    public void testMockData() throws Exception {
        System.out.println("unit test for the weather mock data");

        ClassPathResource resource = new ClassPathResource("mock/weather.json");
        InputStream inputStream = resource.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        WeatherData mockData = mapper.readValue(data, WeatherData.class);
        Assert.assertFalse(mockData == null);
        Assert.assertEquals(mockData.getName(), "Shenzhen");
    }

    @Test
    public void testMockData2() throws Exception {
        System.out.println("unit test for the uvi mock data");

        ClassPathResource resource = new ClassPathResource("mock/uvi.json");
        InputStream inputStream = resource.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        UltravioletIndex mockData = mapper.readValue(data, UltravioletIndex.class);
        Assert.assertEquals(mockData.getDate(), 1548244800);
    }

    @Test
    public void testShowApi() throws Exception {
        System.out.println("unit test for show api");

        Field field = OpenWeatherMapClient.class.getDeclaredField("mockEnabled");
        field.setAccessible(true);
        field.set(openWeatherMapClient, true);
        CurrentWeatherSummary summary = openWeatherMapClient.showCurrentWeather("shenzhen");
        Assert.assertFalse(summary == null);
        Assert.assertEquals(summary.getCityName(), "shenzhen");
    }

}