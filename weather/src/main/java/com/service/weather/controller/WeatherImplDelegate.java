package com.service.weather.controller;

import com.service.weather.entity.objective.CurrentWeatherSummary;
import com.service.weather.util.CacheUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class WeatherImplDelegate
{
    public CurrentWeatherSummary showCurrentWeather(String c)
    {
        String city = StringUtils.isNotBlank(c) ? c : "shenzhen,cn";
        return CacheUtil.DOG.getCurrentWeatherSummary(city);
    }
}
