package com.service.weather.controller;

import com.service.weather.entity.objective.CurrentWeatherSummary;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:26:36.166+08:00")

@RestSchema(schemaId = "weather")
@RequestMapping(path = "/weather", produces = MediaType.APPLICATION_JSON)
public class WeatherImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherImpl.class);

    @Autowired
    private WeatherImplDelegate userCurrentweatherdataDelegate;

    @RequestMapping(value = "/show",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public CurrentWeatherSummary showCurrentWeather(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "user", required = false) String user) {
        LOGGER.info("showCurrentWeather() is called, city = [{}], user = [{}]", city, user);
        return userCurrentweatherdataDelegate.showCurrentWeather(city);
    }
}
