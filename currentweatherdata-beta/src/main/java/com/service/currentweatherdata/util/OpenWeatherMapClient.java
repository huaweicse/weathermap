package com.service.currentweatherdata.util;

import com.service.currentweatherdata.entity.objective.CurrentWeatherSummary;
import com.service.currentweatherdata.entity.original.UltravioletIndex;
import com.service.currentweatherdata.entity.original.WeatherData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenWeatherMapClient
 */
public final class OpenWeatherMapClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentWeatherSummary.class);

    // Metric: Celsius, Imperial: Fahrenheit
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=763d8bb819e1b0fb58c8385ddd26856e&units=metric&q=";

    private static final String UVI_URL = "http://api.openweathermap.org/data/2.5/uvi?appid=763d8bb819e1b0fb58c8385ddd26856e&lat=%s&lon=%s";

    public static CurrentWeatherSummary showCurrentWeather(String c)
    {
        String city = StringUtils.isNotBlank(c) ? c : "shenzhen,cn";
        double lat = 0;
        double lon = 0;

        CurrentWeatherSummary summary = new CurrentWeatherSummary();
        try
        {
            WeatherData weatherData = RestTemplateProxy.INSTANCE.getRestTemplate()
                    .getForObject(StringUtils.join(API_URL, city), WeatherData.class);

            summary.setCityName(weatherData.getName());
            summary.setCountry(weatherData.getSys().getCountry());
            summary.setTemperature(weatherData.getMain().getTemp());
            summary.setImage(weatherData.getWeather().get(0).getIcon());
            summary.setDate(weatherData.getDt());
            summary.setWeather(weatherData.getWeather().get(0).getDescription());
            summary.setWindSpeed(weatherData.getWind().getSpeed());
            summary.setCloudiness(weatherData.getWeather().get(0).getDescription());
            summary.setCloudsDeg(weatherData.getClouds().getAll());
            summary.setPressure(weatherData.getMain().getPressure());
            summary.setHumidity(weatherData.getMain().getHumidity());
            summary.setSunrise(weatherData.getSys().getSunrise());
            summary.setSunset(weatherData.getSys().getSunset());
            summary.setCoordinatesLon(weatherData.getCoord().getLon());
            summary.setCoordinatesLat(weatherData.getCoord().getLat());

            lat = weatherData.getCoord().getLat();
            lon = weatherData.getCoord().getLon();
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to get the current weather data form OpenWeatherMap.", e);
        }

        try
        {
            UltravioletIndex ultravioletIndex = RestTemplateProxy.INSTANCE.getRestTemplate()
                    .getForObject(String.format(UVI_URL, lat, lon), UltravioletIndex.class);

            summary.setUviDate(ultravioletIndex.getDate());
            summary.setUviDateISO(ultravioletIndex.getDateIso());
            summary.setUviValue(ultravioletIndex.getValue());
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to get the ultraviolet index data form OpenWeatherMap.", e);
            summary.setUviDateISO(""); // beta��ʶλ��ǰ̨����
        }

        return summary;
    }
}
