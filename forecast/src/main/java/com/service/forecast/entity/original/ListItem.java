package com.service.forecast.entity.original;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListItem
{

    @JsonProperty("dt")
    private long dt;

    @JsonProperty("dt_txt")
    private String dtTxt;

    @JsonProperty("weather")
    private List<WeatherItem> weather;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("rain")
    private Rain rain;

    public long getDt()
    {
        return dt;
    }

    public void setDt(long dt)
    {
        this.dt = dt;
    }

    public String getDtTxt()
    {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt)
    {
        this.dtTxt = dtTxt;
    }

    public List<WeatherItem> getWeather()
    {
        return weather;
    }

    public void setWeather(List<WeatherItem> weather)
    {
        this.weather = weather;
    }

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public Clouds getClouds()
    {
        return clouds;
    }

    public void setClouds(Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Sys getSys()
    {
        return sys;
    }

    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    public Wind getWind()
    {
        return wind;
    }

    public void setWind(Wind wind)
    {
        this.wind = wind;
    }

    public Rain getRain()
    {
        return rain;
    }

    public void setRain(Rain rain)
    {
        this.rain = rain;
    }
}