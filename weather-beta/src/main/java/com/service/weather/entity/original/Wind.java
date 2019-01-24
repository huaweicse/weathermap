package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "speed",
        "deg"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind
{

    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("deg")
    private Double deg;

    /**
     * No args constructor for use in serialization
     */
    public Wind()
    {
    }

    /**
     * @param speed
     * @param deg
     */
    public Wind(Double speed, Double deg)
    {
        this.speed = speed;
        this.deg = deg;
    }

    /**
     * @return The speed
     */
    @JsonProperty("speed")
    public Double getSpeed()
    {
        return speed;
    }

    /**
     * @param speed The speed
     */
    @JsonProperty("speed")
    public void setSpeed(Double speed)
    {
        this.speed = speed;
    }

    public Wind withSpeed(Double speed)
    {
        this.speed = speed;
        return this;
    }

    /**
     * @return The deg
     */
    @JsonProperty("deg")
    public Double getDeg()
    {
        return deg;
    }

    /**
     * @param deg The deg
     */
    @JsonProperty("deg")
    public void setDeg(Double deg)
    {
        this.deg = deg;
    }

    public Wind withDeg(Double deg)
    {
        this.deg = deg;
        return this;
    }

}
