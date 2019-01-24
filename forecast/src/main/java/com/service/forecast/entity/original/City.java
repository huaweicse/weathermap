package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class City
{

    @JsonProperty("country")
    private String country;

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Coord getCoord()
    {
        return coord;
    }

    public void setCoord(Coord coord)
    {
        this.coord = coord;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}