package com.service.weather.util;

import com.service.weather.entity.objective.CurrentWeatherSummary;
import com.service.weather.entity.original.WeatherData;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * OpenWeatherMapClient
 */
@Component
public class OpenWeatherMapClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentWeatherSummary.class);

	private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

	private static final String DEFAULT = "ShenZhen,CN";

	// Metric: Celsius, Imperial: Fahrenheit
	private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";
	
	private static String URL_HTTP = "http://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";
	
	private static String URL = URL_HTTP;

	@Autowired
	@Qualifier("restProxyTemplate")
	private RestTemplate restTemplate;
	
	public CurrentWeatherSummary showCurrentWeather(String city) {
		city = StringUtils.isNotBlank(city) ? city : DEFAULT;

		CurrentWeatherSummary summary = new CurrentWeatherSummary();
		try {
			WeatherData weatherData = restTemplate
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
			
			swtichURL();
		}

		return summary;
	}

	private void swtichURL() {
		if (URL.equals(URL_HTTP)) {
			URL = URL_HTTPS;
		} else {
			URL = URL_HTTP;
		}

		LOGGER.info("switch url from openweather to: " + URL);
	}
}