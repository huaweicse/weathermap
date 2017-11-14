package com.service.weather.entity.objective;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherSummary
{

    /**
     * cityName : HaiKou
     * temperature : 26
     * image : 04d
     * date : 1509433200
     * weather : broken clouds
     * windSpeed : 7
     * cloudiness : broken clouds
     * cloudsDeg : 75
     * pressure : 1016
     * humidity : 69
     * sunrise : 1509403182
     * sunset : 1509444267
     * coordinatesLat : 20.05
     * coordinatesLon : 110.34
     */

    private String cityName;

    private String country;

    private double temperature;

    private String image;

    private long date;

    private String weather;

    private double windSpeed;

    private String cloudiness;

    private double cloudsDeg;

    private double pressure;

    private double humidity;

    private long sunrise;

    private long sunset;

    private double coordinatesLat;

    private double coordinatesLon;

    private long currentTime = System.currentTimeMillis();

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public long getDate()
    {
        return date;
    }

    public void setDate(long date)
    {
        this.date = date;
    }

    public String getWeather()
    {
        return weather;
    }

    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public String getCloudiness()
    {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness)
    {
        this.cloudiness = cloudiness;
    }

    public double getCloudsDeg()
    {
        return cloudsDeg;
    }

    public void setCloudsDeg(double cloudsDeg)
    {
        this.cloudsDeg = cloudsDeg;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public long getSunrise()
    {
        return sunrise;
    }

    public void setSunrise(long sunrise)
    {
        this.sunrise = sunrise;
    }

    public long getSunset()
    {
        return sunset;
    }

    public void setSunset(long sunset)
    {
        this.sunset = sunset;
    }

    public double getCoordinatesLat()
    {
        return coordinatesLat;
    }

    public void setCoordinatesLat(double coordinatesLat)
    {
        this.coordinatesLat = coordinatesLat;
    }

    public double getCoordinatesLon()
    {
        return coordinatesLon;
    }

    public void setCoordinatesLon(double coordinatesLon)
    {
        this.coordinatesLon = coordinatesLon;
    }

    public long getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(long currentTime)
    {
        this.currentTime = currentTime;
    }

    @Override
    public String toString()
    {
        return "CurrentWeatherSummary{" +
                "cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", temperature=" + temperature +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", weather='" + weather + '\'' +
                ", windSpeed=" + windSpeed +
                ", cloudiness='" + cloudiness + '\'' +
                ", cloudsDeg=" + cloudsDeg +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", coordinatesLat=" + coordinatesLat +
                ", coordinatesLon=" + coordinatesLon +
                '}';
    }
}
