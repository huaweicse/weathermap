package com.service.forecast.controller;

import com.service.forecast.entity.objective.ForecastSummary;
import com.service.forecast.util.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ForecastImplDelegate
{
    public ForecastSummary showForecastWeather(String c)
    {
        String city = StringUtils.isNotBlank(c) ? c : "shenzhen,cn";
        return CacheUtil.DOG.getForecastWeatherSummary(city);
    }
}
