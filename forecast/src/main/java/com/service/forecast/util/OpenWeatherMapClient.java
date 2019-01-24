package com.service.forecast.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.forecast.entity.objective.DateListItem;
import com.service.forecast.entity.objective.ForecastSummary;
import com.service.forecast.entity.original.ForecastData;
import com.service.forecast.entity.original.ListItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * OpenWeatherMapClient
 */
@Component
public class OpenWeatherMapClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    private static final Random random = new Random();

    private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

    private static final String DEFAULT = "ShenZhen,CN";

    // Metric: Celsius, Imperial: Fahrenheit
    private static String URL_HTTP = "http://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

    private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/forecast?appid=%s&units=metric&q=%s";

    private static String URL = URL_HTTP;

    private static ForecastData MOCK_FORECAST_DATA = null;

    static {
        try {
            ClassPathResource resource = new ClassPathResource("mock/forecast.json");
            InputStream inputStream = resource.getInputStream();
            String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            MOCK_FORECAST_DATA = mapper.readValue(data, ForecastData.class);
        } catch (IOException e) {
            LOGGER.error("Failed to get mock data.", e);
        }
    }

    @Value("${mock.enabled}")
    private boolean mockEnabled = false;

    @Autowired
    @Qualifier("restProxyTemplate")
    private RestTemplate restTemplate;

    public ForecastSummary showForecastWeather(String city) {
        long l = System.currentTimeMillis();
        LOGGER.info("begin showForecastWeather from openweather");
        city = StringUtils.isNotBlank(city) ? city : DEFAULT;

        ForecastSummary summary = new ForecastSummary();
        try {
            ForecastData forecastData = null;
            if (mockEnabled) {
                forecastData = MOCK_FORECAST_DATA;
                LOGGER.info("using mock data, end showForecastWeather from openweather cost " + (System.currentTimeMillis() - l));
                summary.setCityName(city);
                summary.setCountry(forecastData.getCity().getCountry());
                summary.setCoordinatesLat(forecastData.getCity().getCoord().getLat());
                summary.setCoordinatesLon(forecastData.getCity().getCoord().getLon());
                List<DateListItem> dateListItemList = new ArrayList<DateListItem>();
                for (ListItem i : forecastData.getList()) {
                    dateListItemList.add(toDateListItemMock(i));
                }
                summary.setDateList(dateListItemList);
            } else {
                forecastData = restTemplate.getForObject(String.format(URL, APP_KEY, city),
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
            }
        } catch (Exception e) {
            LOGGER.error("Failed to get the forecast weather data form OpenWeatherMap with " + city, e);

            swtichURL();
        }

        return summary;
    }

    private DateListItem toDateListItem(ListItem item) {
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

    private DateListItem toDateListItemMock(ListItem item) {
        DateListItem dateListItem = new DateListItem();
        dateListItem.setDate(item.getDt());
        dateListItem.setDateTxt(item.getDtTxt());
        dateListItem.setWeather(item.getWeather().get(0).getDescription());
        dateListItem.setImage(item.getWeather().get(0).getIcon());
        dateListItem.setTemperature(randomDouble(item.getMain().getTemp(), 10, 5));
        dateListItem.setTemperatureMin(randomDouble(item.getMain().getTempMin(), 10, 5));
        dateListItem.setTemperatureMax(randomDouble(item.getMain().getTempMax(), 10, 5));
        dateListItem.setWindSpeed(randomDouble(item.getWind().getSpeed(), 2, 1));
        dateListItem.setCloudsDeg(item.getClouds().getAll());
        dateListItem.setPressure(randomDouble(item.getMain().getPressure(), 100, 50));
        dateListItem.setHumidity(randomDouble(item.getMain().getHumidity(), 30, 15));
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

    private double randomDouble(double v, int m, int n) {
        return Double.valueOf(String.format("%.2f", v + Math.floor(random.nextDouble() * m - n)));
    }
}
