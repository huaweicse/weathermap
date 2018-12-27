package com.service.forecast.controller;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:16:52.801+08:00")

@RestSchema(schemaId = "forecast")
@RequestMapping(path = "/forecast", produces = MediaType.APPLICATION_JSON)
public class ForecastImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(ForecastImpl.class);

  @Autowired
  private ForecastImplDelegate userForecastweatherdataDelegate;

  private int latencyTime = 0;

  @PostConstruct
  public void init() {
    LOGGER.info("Init success");
    DynamicIntProperty latency = DynamicPropertyFactory.getInstance().getIntProperty("latency", 0);
    latency.addCallback(() -> {
      latencyTime = latency.get();
      LOGGER.info("Latency time change to {}", latencyTime);
    });
  }

  @RequestMapping(value = "/show",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public String getForecast(@RequestParam(value = "city", required = true) String city) {
    LOGGER.info("getForecast() is called, city = [{}]", city);
    if (latencyTime > 0) {
      try {
        Thread.sleep(latencyTime);
      } catch (Exception e) {

      }
    }

    return new JsonObject(Json.encode(userForecastweatherdataDelegate.showForecastWeather(city))).toString();
  }
}
