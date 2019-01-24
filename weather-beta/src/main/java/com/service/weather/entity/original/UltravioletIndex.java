package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UltravioletIndex
{

    @JsonProperty("date")
    private int date;

    @JsonProperty("date_iso")
    private String dateIso;

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("value")
    private double value;

    @JsonProperty("lat")
    private double lat;

    public void setDate(int date)
    {
        this.date = date;
    }

    public int getDate()
    {
        return date;
    }

    public void setDateIso(String dateIso)
    {
        this.dateIso = dateIso;
    }

    public String getDateIso()
    {
        return dateIso;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public double getLon()
    {
        return lon;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return value;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLat()
    {
        return lat;
    }
}