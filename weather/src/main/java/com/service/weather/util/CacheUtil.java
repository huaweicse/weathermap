package com.service.weather.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.weather.entity.objective.CurrentWeatherSummary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache
 */
public enum CacheUtil
{
    DOG;

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentWeatherSummary.class);

    private static final int TIME_SPAN = 1800 * 1000;

    private Map<String, CurrentWeatherSummary> cacheMap = new ConcurrentHashMap<String, CurrentWeatherSummary>();

    public CurrentWeatherSummary getCurrentWeatherSummary(String kk)
    {
        CurrentWeatherSummary su = cacheMap.computeIfAbsent(kk, (nm) -> {
            LOGGER.info("Achieve the forecast weather data from OpenWeatherMap");
            return OpenWeatherMapClient.showCurrentWeather(nm);
        });

        if (System.currentTimeMillis() - su.getCurrentTime() > TIME_SPAN)
        {
            su = cacheMap.computeIfPresent(kk, (nm, vl) -> {
                LOGGER.info("Retrieve the forecast weather data from OpenWeatherMap");
                return OpenWeatherMapClient.showCurrentWeather(nm);
            });
        }

        return su;
    }
}
