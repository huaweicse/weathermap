package com.service.weather.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.weather.entity.objective.CurrentWeatherSummary;
import com.service.weather.entity.original.WeatherData;
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
import java.util.Random;
import java.util.stream.Collectors;

/**
 * OpenWeatherMapClient
 */
@Component
public class OpenWeatherMapClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentWeatherSummary.class);

    private static final Random random = new Random();

    private static final String APP_KEY = "763d8bb819e1b0fb58c8385ddd26856e";

    private static final String DEFAULT = "ShenZhen,CN";

    // Metric: Celsius, Imperial: Fahrenheit
    private static String URL_HTTPS = "https://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";

    private static String URL_HTTP = "http://api.openweathermap.org/data/2.5/weather?appid=%s&q=%s&units=metric";

    private static String URL = URL_HTTP;

    private static WeatherData MOCK_WEATHER_DATA = null;

    static {
        try {
            ClassPathResource resource = new ClassPathResource("mock/weather.json");
            InputStream inputStream = resource.getInputStream();
            String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            MOCK_WEATHER_DATA = mapper.readValue(data, WeatherData.class);
        } catch (IOException e) {
            LOGGER.error("Failed to get mock data.", e);
        }
    }

    @Value("${mock.enabled}")
    private boolean mockEnabled = false;

    @Autowired
    @Qualifier("restProxyTemplate")
    private RestTemplate restTemplate;

    public CurrentWeatherSummary showCurrentWeather(String city) {
        city = StringUtils.isNotBlank(city) ? city : DEFAULT;

        CurrentWeatherSummary summary = new CurrentWeatherSummary();
        try {
            WeatherData weatherData = null;
            if (mockEnabled) {
                weatherData = MOCK_WEATHER_DATA;

                summary.setCityName(city);
                summary.setCountry(weatherData.getSys().getCountry());
                summary.setTemperature(randomDouble(weatherData.getMain().getTemp(), 10, 5));
                summary.setImage(weatherData.getWeather().get(0).getIcon());
                summary.setDate(weatherData.getDt());
                summary.setWeather(weatherData.getWeather().get(0).getDescription());
                summary.setWindSpeed(randomDouble(weatherData.getWind().getSpeed(), 2, 1));
                summary.setCloudiness(weatherData.getWeather().get(0).getDescription());
                summary.setCloudsDeg(weatherData.getClouds().getAll());
                summary.setPressure(randomDouble(weatherData.getMain().getPressure(), 100, 50));
                summary.setHumidity(randomDouble(weatherData.getMain().getHumidity(), 30, 15));
                summary.setSunrise(weatherData.getSys().getSunrise());
                summary.setSunset(weatherData.getSys().getSunset());
                summary.setCoordinatesLon(weatherData.getCoord().getLon());
                summary.setCoordinatesLat(weatherData.getCoord().getLat());
            } else {
                weatherData = restTemplate
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
            }
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

    private double randomDouble(double v, int m, int n) {
        return Double.valueOf(String.format("%.2f", v + Math.floor(random.nextDouble() * m - n)));
    }
}