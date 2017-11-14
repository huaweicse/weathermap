package com.service.forecastweatherdata.entity.original;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class ForecastData
{

    @JsonProperty("city")
    private City city;

    @JsonProperty("cnt")
    private int cnt;

    @JsonProperty("cod")
    private String cod;

    @JsonProperty("message")
    private double message;

    @JsonProperty("list")
    private List<ListItem> list;

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public int getCnt()
    {
        return cnt;
    }

    public void setCnt(int cnt)
    {
        this.cnt = cnt;
    }

    public String getCod()
    {
        return cod;
    }

    public void setCod(String cod)
    {
        this.cod = cod;
    }

    public double getMessage()
    {
        return message;
    }

    public void setMessage(double message)
    {
        this.message = message;
    }

    public List<ListItem> getList()
    {
        return list;
    }

    public void setList(List<ListItem> list)
    {
        this.list = list;
    }
}