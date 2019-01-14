package com.service.forecast.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.forecast.entity.objective.ForecastSummary;

/**
 * Cache
 */
@Component
public class CacheUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

    private static final int TIME_SPAN = 1800 * 1000;

    private Map<String, ForecastSummary> cacheMap = new ConcurrentHashMap<String, ForecastSummary>();

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;
    
    public ForecastSummary getForecastWeatherSummary(String kk)
    {
        ForecastSummary su = cacheMap.computeIfAbsent(kk, (nm) -> {
            LOGGER.info("Achieve the forecast data from OpenWeatherMap :" + kk);
            return openWeatherMapClient.showForecastWeather(nm);
        });

        if (System.currentTimeMillis() - su.getCurrentTime() > TIME_SPAN)
        {
            su = cacheMap.computeIfPresent(kk, (nm, vl) -> {
                LOGGER.info("Retrieve the forecast weather data from OpenWeatherMap : " + kk);
                return openWeatherMapClient.showForecastWeather(nm);
            });
        }

        if (StringUtils.isBlank(su.getCityName()))
        {
            cacheMap.remove(kk);
        }

        return su;
    }
}
