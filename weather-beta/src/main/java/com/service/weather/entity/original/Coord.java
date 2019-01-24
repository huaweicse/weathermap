package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "lon",
        "lat"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord
{

    @JsonProperty("lon")
    private Double lon;

    @JsonProperty("lat")
    private Double lat;

    /**
     * No args constructor for use in serialization
     */
    public Coord()
    {
    }

    /**
     * @param lon
     * @param lat
     */
    public Coord(Double lon, Double lat)
    {
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * @return The lon
     */
    @JsonProperty("lon")
    public Double getLon()
    {
        return lon;
    }

    /**
     * @param lon The lon
     */
    @JsonProperty("lon")
    public void setLon(Double lon)
    {
        this.lon = lon;
    }

    public Coord withLon(Double lon)
    {
        this.lon = lon;
        return this;
    }

    /**
     * @return The lat
     */
    @JsonProperty("lat")
    public Double getLat()
    {
        return lat;
    }

    /**
     * @param lat The lat
     */
    @JsonProperty("lat")
    public void setLat(Double lat)
    {
        this.lat = lat;
    }

    public Coord withLat(Double lat)
    {
        this.lat = lat;
        return this;
    }

}
