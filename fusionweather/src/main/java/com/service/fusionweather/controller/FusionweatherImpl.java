package com.service.fusionweather.controller;

import com.service.fusionweather.entity.FusionWeatherSummary;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:27:01.678+08:00")

@RestSchema(schemaId = "fusionweather")
@RequestMapping(path = "/fusionweather", produces = MediaType.APPLICATION_JSON)
public class FusionweatherImpl
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FusionweatherImpl.class);

    @Autowired
    private FusionweatherImplDelegate userFusionweatherdataDelegate;

    @RequestMapping(value = "/show",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public FusionWeatherSummary show(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "user", required = false) String user)
    {
        LOGGER.info("show() is called, city = [{}], user = [{}]", city, user);
        return userFusionweatherdataDelegate.showFusionWeather(city, user);
    }
}
