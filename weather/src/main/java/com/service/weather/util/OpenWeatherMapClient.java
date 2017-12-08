package com.service.weather.util;

import com.service.weather.entity.objective.CurrentWeatherSummary;
import com.service.weather.entity.original.WeatherData;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenWeatherMapClient
 */
public final class OpenWeatherMapClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentWeatherSummary.class);

	private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

	private static final String DEFAULT = "ShenZhen,CN";

	// Metric: Celsius, Imperial: Fahrenheit
	private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";
	
	private static String URL = "http://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";

	static {
		testURL();
	}

	public static CurrentWeatherSummary showCurrentWeather(String city) {
		city = StringUtils.isNotBlank(city) ? city : DEFAULT;

		CurrentWeatherSummary summary = new CurrentWeatherSummary();
		try {
			WeatherData weatherData = RestTemplateProxy.INSTANCE.getRestTemplate()
					.getForObject(String.format(URL, APP_KEY, city), WeatherData.class);

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
		} catch (Exception e) {
			LOGGER.error("Failed to get the current weather data from OpenWeatherMap with " + city, e);
		}

		return summary;
	}

	private static void testURL() {
		try {
			RestTemplateProxy.INSTANCE.getRestTemplate().getForObject(String.format(URL, APP_KEY, DEFAULT),
					WeatherData.class);
		} catch (Exception ex) {
			URL = URL_HTTPS;
		}
		LOGGER.info("URL = " + URL);
	}
}
