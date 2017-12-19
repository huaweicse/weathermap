package com.service.forecast.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.service.forecast.entity.objective.DateListItem;
import com.service.forecast.entity.objective.ForecastSummary;
import com.service.forecast.entity.original.ForecastData;
import com.service.forecast.entity.original.ListItem;

/**
 * OpenWeatherMapClient
 */
@Component
public class OpenWeatherMapClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapClient.class);

	private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

	private static final String DEFAULT = "ShenZhen,CN";

	// Metric: Celsius, Imperial: Fahrenheit
	private static String URL_HTTP = "https://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

	private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

	private static String URL = URL_HTTP;

	@Autowired
	private RestTemplate restTemplate;

	public ForecastSummary showForecastWeather(String city) {
		long l = System.currentTimeMillis();
		LOGGER.info("begin showForecastWeather from openweather");
		city = StringUtils.isNotBlank(city) ? city : DEFAULT;

		ForecastSummary summary = new ForecastSummary();
		try {
			ForecastData forecastData = restTemplate.getForObject(String.format(URL, APP_KEY, city),
					ForecastData.class);

			LOGGER.info("end showForecastWeather from openweather cost " + (System.currentTimeMillis() - l));

			summary.setCityName(forecastData.getCity().getName());
			summary.setCountry(forecastData.getCity().getCountry());
			summary.setCoordinatesLat(forecastData.getCity().getCoord().getLat());
			summary.setCoordinatesLon(forecastData.getCity().getCoord().getLon());

			List<DateListItem> dateListItemList = new ArrayList<DateListItem>();
			for (ListItem i : forecastData.getList()) {
				dateListItemList.add(toDateListItem(i));
			}
			summary.setDateList(dateListItemList);
		} catch (Exception e) {
			LOGGER.error("Failed to get the forecast weather data form OpenWeatherMap with " + city, e);

			swtichURL();
		}

		return summary;
	}

	private static DateListItem toDateListItem(ListItem item) {
		DateListItem dateListItem = new DateListItem();
		dateListItem.setDate(item.getDt());
		dateListItem.setDateTxt(item.getDtTxt());
		dateListItem.setWeather(item.getWeather().get(0).getDescription());
		dateListItem.setImage(item.getWeather().get(0).getIcon());
		dateListItem.setTemperature(item.getMain().getTemp());
		dateListItem.setTemperatureMin(item.getMain().getTempMin());
		dateListItem.setTemperatureMax(item.getMain().getTempMax());
		dateListItem.setWindSpeed(item.getWind().getSpeed());
		dateListItem.setCloudsDeg(item.getClouds().getAll());
		dateListItem.setPressure(item.getMain().getPressure());
		dateListItem.setHumidity(item.getMain().getHumidity());
		dateListItem.setRain3h(item.getRain() != null ? item.getRain().getJsonMember3h() : 0);

		return dateListItem;
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
