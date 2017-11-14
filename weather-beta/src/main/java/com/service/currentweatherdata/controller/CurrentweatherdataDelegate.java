package com.service.currentweatherdata.controller;

import com.service.currentweatherdata.entity.objective.CurrentWeatherSummary;
import com.service.currentweatherdata.util.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrentweatherdataDelegate
{
    public CurrentWeatherSummary showCurrentWeather(String c)
    {
        String city = StringUtils.isNotBlank(c) ? c : "shenzhen,cn";
        return CacheUtil.DOG.getCurrentWeatherSummary(city);
    }
}
