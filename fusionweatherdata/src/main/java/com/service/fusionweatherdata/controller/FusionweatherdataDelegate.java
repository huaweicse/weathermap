package com.service.fusionweatherdata.controller;

import com.service.fusionweatherdata.entity.CurrentWeatherSummary;
import com.service.fusionweatherdata.entity.ForecastWeatherSummary;
import com.service.fusionweatherdata.entity.FusionWeatherSummary;
import io.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class FusionweatherdataDelegate
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FusionweatherdataDelegate.class);

    private static final RestTemplate invoker = RestTemplateBuilder.create();

    public FusionWeatherSummary showFusionWeather(String city, String user)
    {
        FusionWeatherSummary summary = new FusionWeatherSummary();
        summary.setCurrentWeather(achieveCurrentWeatherSummary(city, user));
        summary.setForecastWeather(achieveForecastWeatherSummary(city));

        return summary;
    }

    private CurrentWeatherSummary achieveCurrentWeatherSummary(String city, String user)
    {
        //        final String url = StringUtils.isNotBlank(user) && user.equalsIgnoreCase("beta") ?
        //                "http://127.0.0.1:13093/currentweatherdata/show?city=" + city :
        //                "http://127.0.0.1:13090/currentweatherdata/show?city=" + city;
        String url = "cse://currentweatherdata/currentweatherdata/show?city=" + city;
        if (!StringUtils.isEmpty(user))
        {
            url = url + "&user=" + user;
        }
        CurrentWeatherSummary su;
        try
        {
            String s = invoker.getForObject(url, String.class, new Object());
            su = Json.decodeValue(s, CurrentWeatherSummary.class);
            System.out.println("-----  " + s);
        }
        catch (Exception e)
        {
            LOGGER.error("FusionweatherdataDelegate>> Failed to achieve the current weather summary", e);
            su = new CurrentWeatherSummary();
        }
        return su;
    }

    private ForecastWeatherSummary achieveForecastWeatherSummary(String city)
    {
        //final String url = "http://127.0.0.1:13091/forecastweatherdata/show?city=" + city;
        final String url = "cse://forecastweatherdata/forecastweatherdata/show?city=" + city;
        ForecastWeatherSummary su;
        try
        {
            String s = invoker.getForObject(url, String.class, new Object());
            su = Json.decodeValue(s, ForecastWeatherSummary.class);
        }
        catch (Exception e)
        {
            LOGGER.error("FusionweatherdataDelegate>> Failed to achieve the forecast weather summary", e);
            su = new ForecastWeatherSummary();
        }
        return su;
    }
}
