package com.service.weather.controller;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.service.weather.entity.objective.CurrentWeatherSummary;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:26:36.166+08:00")

@RestSchema(schemaId = "weather")
@RequestMapping(path = "/weather", produces = MediaType.APPLICATION_JSON)
public class WeatherImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherImpl.class);

    @Autowired
    private WeatherImplDelegate userCurrentweatherdataDelegate;

    private int latencyTime = 0;

    @PostConstruct
    public void init() {
        LOGGER.info("Init success");
        DynamicIntProperty latency = DynamicPropertyFactory.getInstance().getIntProperty("latency", 0);
        latency.addCallback(() -> {
            latencyTime = latency.get();
            LOGGER.info("Latency time change to {}", latencyTime);
        });
        latencyTime = latency.get();
    }

    @RequestMapping(value = "/show",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public CurrentWeatherSummary showCurrentWeather(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "user", required = false) String user) {
        if (latencyTime > 0) {
            try {
                Thread.sleep(latencyTime);
            } catch (Exception e) {

            }
        }
        LOGGER.info("showCurrentWeather() is called, city = [{}], user = [{}]", city, user);
        return userCurrentweatherdataDelegate.showCurrentWeather(city);
    }
}
