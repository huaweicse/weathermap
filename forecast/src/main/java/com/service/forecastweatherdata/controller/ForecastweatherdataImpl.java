package com.service.forecastweatherdata.controller;

import io.servicecomb.provider.rest.common.RestSchema;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:16:52.801+08:00")

@RestSchema(schemaId = "forecastweatherdata")
@RequestMapping(path = "/forecastweatherdata", produces = MediaType.APPLICATION_JSON)
public class ForecastweatherdataImpl
{

    @Autowired
    private ForecastweatherdataDelegate userForecastweatherdataDelegate;

    @RequestMapping(value = "/show",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public String helloworld(@RequestParam(value = "city", required = true) String city)
    {

        return new JsonObject(Json.encode(userForecastweatherdataDelegate.showForecastWeather(city))).toString();
    }

}
