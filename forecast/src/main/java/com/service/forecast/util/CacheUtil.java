package com.service.forecast.util;

import com.service.forecast.controller.ForecastImplDelegate;
import com.service.forecast.entity.objective.ForecastSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache
 */
public enum CacheUtil
{
    DOG;

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastImplDelegate.class);

    private static final int TIME_SPAN = 1800 * 1000;

    private Map<String, ForecastSummary> cacheMap = new ConcurrentHashMap<String, ForecastSummary>();

    public ForecastSummary getForecastWeatherSummary(String kk)
    {
        ForecastSummary su = cacheMap.computeIfAbsent(kk, (nm) -> {
            LOGGER.info("Achieve the forecast weather data from OpenWeatherMap");
            return OpenWeatherMapClient.showForecastWeather(nm);
        });

        if (System.currentTimeMillis() - su.getCurrentTime() > TIME_SPAN)
        {
            su = cacheMap.computeIfPresent(kk, (nm, vl) -> {
                LOGGER.info("Retrieve the forecast weather data from OpenWeatherMap");
                return OpenWeatherMapClient.showForecastWeather(nm);
            });
        }

        return su;
    }
}
