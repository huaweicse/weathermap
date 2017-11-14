package com.service.forecastweatherdata.util;

import com.service.forecastweatherdata.controller.ForecastweatherdataDelegate;
import com.service.forecastweatherdata.entity.objective.ForecastWeatherSummary;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastweatherdataDelegate.class);

    private static final int TIME_SPAN = 1800 * 1000;

    private Map<String, ForecastWeatherSummary> cacheMap = new ConcurrentHashMap<String, ForecastWeatherSummary>();

    public ForecastWeatherSummary getForecastWeatherSummary(String kk)
    {
        ForecastWeatherSummary su = cacheMap.computeIfAbsent(kk, (nm) -> {
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
