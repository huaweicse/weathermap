package com.service.fusionweather.entity;

/**
 * FusionWeatherSummary
 */
public class FusionWeatherSummary
{
    private CurrentWeatherSummary currentWeather = new CurrentWeatherSummary();

    private ForecastWeatherSummary forecastWeather = new ForecastWeatherSummary();

    public CurrentWeatherSummary getCurrentWeather()
    {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeatherSummary currentWeather)
    {
        this.currentWeather = currentWeather;
    }

    public ForecastWeatherSummary getForecastWeather()
    {
        return forecastWeather;
    }

    public void setForecastWeather(ForecastWeatherSummary forecastWeather)
    {
        this.forecastWeather = forecastWeather;
    }
}
