package com.service.forecast.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.forecast.controller.ForecastImplDelegate;
import com.service.forecast.entity.objective.DateListItem;
import com.service.forecast.entity.objective.ForecastSummary;
import com.service.forecast.entity.original.ForecastData;
import com.service.forecast.entity.original.ListItem;

/**
 * OpenWeatherMapClient
 */
public final class OpenWeatherMapClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastImplDelegate.class);

	private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

	private static final String DEFAULT = "ShenZhen,CN";

	// Metric: Celsius, Imperial: Fahrenheit
	private static String URL = "http://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

	private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

	static {
		testURL();
	}

	public static ForecastSummary showForecastWeather(String city) {
		city = StringUtils.isNotBlank(city) ? city : DEFAULT;
		
		ForecastSummary summary = new ForecastSummary();
		try {
			ForecastData forecastData = RestTemplateProxy.INSTANCE.getRestTemplate()
					.getForObject(String.format(URL, APP_KEY, city), ForecastData.class);

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

	private static void testURL() {
		try {
			RestTemplateProxy.INSTANCE.getRestTemplate().getForObject(String.format(URL, APP_KEY, DEFAULT),
					ForecastData.class);
		} catch (Exception ex) {
			URL = URL_HTTPS;
		}
		LOGGER.info("URL = " + URL);
	}
}
