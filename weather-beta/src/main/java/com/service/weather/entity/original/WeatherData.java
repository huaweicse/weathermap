package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "wind",
        "rain",
        "clouds",
        "dt",
        "sys",
        "id",
        "name",
        "cod"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData
{

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private List<Weather> weather = new ArrayList<Weather>();

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private Long dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private Long cod;

    /**
     * No args constructor for use in serialization
     */
    public WeatherData()
    {
    }

    /**
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param sys
     * @param name
     * @param base
     * @param weather
     * @param rain
     * @param main
     */
    public WeatherData(Coord coord, List<Weather> weather, String base, Main main, Wind wind, Rain rain, Clouds clouds, Long dt, Sys sys, Long id, String name, Long cod)
    {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    /**
     * @return The coord
     */
    @JsonProperty("coord")
    public Coord getCoord()
    {
        return coord;
    }

    /**
     * @param coord The coord
     */
    @JsonProperty("coord")
    public void setCoord(Coord coord)
    {
        this.coord = coord;
    }

    public WeatherData withCoord(Coord coord)
    {
        this.coord = coord;
        return this;
    }

    /**
     * @return The weather
     */
    @JsonProperty("weather")
    public List<Weather> getWeather()
    {
        return weather;
    }

    /**
     * @param weather The weather
     */
    @JsonProperty("weather")
    public void setWeather(List<Weather> weather)
    {
        this.weather = weather;
    }

    public WeatherData withWeather(List<Weather> weather)
    {
        this.weather = weather;
        return this;
    }

    /**
     * @return The base
     */
    @JsonProperty("base")
    public String getBase()
    {
        return base;
    }

    /**
     * @param base The base
     */
    @JsonProperty("base")
    public void setBase(String base)
    {
        this.base = base;
    }

    public WeatherData withBase(String base)
    {
        this.base = base;
        return this;
    }

    /**
     * @return The main
     */
    @JsonProperty("main")
    public Main getMain()
    {
        return main;
    }

    /**
     * @param main The main
     */
    @JsonProperty("main")
    public void setMain(Main main)
    {
        this.main = main;
    }

    public WeatherData withMain(Main main)
    {
        this.main = main;
        return this;
    }

    /**
     * @return The wind
     */
    @JsonProperty("wind")
    public Wind getWind()
    {
        return wind;
    }

    /**
     * @param wind The wind
     */
    @JsonProperty("wind")
    public void setWind(Wind wind)
    {
        this.wind = wind;
    }

    public WeatherData withWind(Wind wind)
    {
        this.wind = wind;
        return this;
    }

    /**
     * @return The rain
     */
    @JsonProperty("rain")
    public Rain getRain()
    {
        return rain;
    }

    /**
     * @param rain The rain
     */
    @JsonProperty("rain")
    public void setRain(Rain rain)
    {
        this.rain = rain;
    }

    public WeatherData withRain(Rain rain)
    {
        this.rain = rain;
        return this;
    }

    /**
     * @return The clouds
     */
    @JsonProperty("clouds")
    public Clouds getClouds()
    {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    @JsonProperty("clouds")
    public void setClouds(Clouds clouds)
    {
        this.clouds = clouds;
    }

    public WeatherData withClouds(Clouds clouds)
    {
        this.clouds = clouds;
        return this;
    }

    /**
     * @return The dt
     */
    @JsonProperty("dt")
    public Long getDt()
    {
        return dt;
    }

    /**
     * @param dt The dt
     */
    @JsonProperty("dt")
    public void setDt(Long dt)
    {
        this.dt = dt;
    }

    public WeatherData withDt(Long dt)
    {
        this.dt = dt;
        return this;
    }

    /**
     * @return The sys
     */
    @JsonProperty("sys")
    public Sys getSys()
    {
        return sys;
    }

    /**
     * @param sys The sys
     */
    @JsonProperty("sys")
    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    public WeatherData withSys(Sys sys)
    {
        this.sys = sys;
        return this;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Long id)
    {
        this.id = id;
    }

    public WeatherData withId(Long id)
    {
        this.id = id;
        return this;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    public WeatherData withName(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * @return The cod
     */
    @JsonProperty("cod")
    public Long getCod()
    {
        return cod;
    }

    /**
     * @param cod The cod
     */
    @JsonProperty("cod")
    public void setCod(Long cod)
    {
        this.cod = cod;
    }

    public WeatherData withCod(Long cod)
    {
        this.cod = cod;
        return this;
    }

}
