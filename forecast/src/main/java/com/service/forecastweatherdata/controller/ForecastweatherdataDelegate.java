package com.service.forecastweatherdata.controller;

import com.service.forecastweatherdata.entity.objective.ForecastWeatherSummary;
import com.service.forecastweatherdata.util.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ForecastweatherdataDelegate
{
    public ForecastWeatherSummary showForecastWeather(String c)
    {
        String city = StringUtils.isNotBlank(c) ? c : "shenzhen,cn";
        return CacheUtil.DOG.getForecastWeatherSummary(city);
    }
}
