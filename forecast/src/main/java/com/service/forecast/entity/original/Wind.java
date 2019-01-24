package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind
{

    @JsonProperty("deg")
    private double deg;

    @JsonProperty("speed")
    private double speed;

    public double getDeg()
    {
        return deg;
    }

    public void setDeg(double deg)
    {
        this.deg = deg;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
}