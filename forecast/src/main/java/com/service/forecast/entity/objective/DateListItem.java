package com.service.forecast.entity.objective;

public class DateListItem
{
    private long date;

    private String image;

    private String dateTxt;

    private double temperatureMax;

    private String weather;

    private double temperature;

    private double temperatureMin;

    private double humidity;

    private double pressure;

    private double windSpeed;

    private double cloudsDeg;

    private double rain3h;

    public void setDate(long date)
    {
        this.date = date;
    }

    public long getDate()
    {
        return date;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }

    public void setDateTxt(String dateTxt)
    {
        this.dateTxt = dateTxt;
    }

    public String getDateTxt()
    {
        return dateTxt;
    }

    public void setTemperatureMax(double temperatureMax)
    {
        this.temperatureMax = temperatureMax;
    }

    public double getTemperatureMax()
    {
        return temperatureMax;
    }

    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    public String getWeather()
    {
        return weather;
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public void setTemperatureMin(double temperatureMin)
    {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMin()
    {
        return temperatureMin;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setWindSpeed(double windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public void setCloudsDeg(double cloudsDeg)
    {
        this.cloudsDeg = cloudsDeg;
    }

    public double getCloudsDeg()
    {
        return cloudsDeg;
    }

    public double getRain3h()
    {
        return rain3h;
    }

    public void setRain3h(double rain3h)
    {
        this.rain3h = rain3h;
    }
}
