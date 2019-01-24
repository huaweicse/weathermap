package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds
{

    @JsonProperty("all")
    private double all;

    public double getAll()
    {
        return all;
    }

    public void setAll(double all)
    {
        this.all = all;
    }
}