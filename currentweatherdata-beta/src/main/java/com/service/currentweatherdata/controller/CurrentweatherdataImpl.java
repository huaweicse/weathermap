package com.service.currentweatherdata.controller;

import io.servicecomb.provider.rest.common.RestSchema;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:26:36.166+08:00")

@RestSchema(schemaId = "currentweatherdata")
@RequestMapping(path = "/currentweatherdata", produces = MediaType.APPLICATION_JSON)
public class CurrentweatherdataImpl
{

    @Autowired
    private CurrentweatherdataDelegate userCurrentweatherdataDelegate;

    @RequestMapping(value = "/show",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public String showCurrentWeather(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "user", required = true) String user)
    {
        return new JsonObject(Json.encode(userCurrentweatherdataDelegate.showCurrentWeather(city))).toString();
    }

}
