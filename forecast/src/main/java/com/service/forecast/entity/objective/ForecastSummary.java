package com.service.forecast.entity.objective;

import java.util.List;

public class ForecastSummary
{
    private String country;

    private String cityName;

    private double coordinatesLon;

    private double coordinatesLat;

    private List<DateListItem> dateList;

    private long currentTime = System.currentTimeMillis();

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCoordinatesLon(double coordinatesLon)
    {
        this.coordinatesLon = coordinatesLon;
    }

    public double getCoordinatesLon()
    {
        return coordinatesLon;
    }

    public void setCoordinatesLat(double coordinatesLat)
    {
        this.coordinatesLat = coordinatesLat;
    }

    public double getCoordinatesLat()
    {
        return coordinatesLat;
    }

    public void setDateList(List<DateListItem> dateList)
    {
        this.dateList = dateList;
    }

    public List<DateListItem> getDateList()
    {
        return dateList;
    }

    public long getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(long currentTime)
    {
        this.currentTime = currentTime;
    }
}