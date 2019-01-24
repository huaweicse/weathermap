package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "temp",
        "pressure",
        "humidity",
        "temp_min",
        "temp_max"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main
{

    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("pressure")
    private Long pressure;

    @JsonProperty("humidity")
    private Long humidity;

    @JsonProperty("temp_min")
    private Double tempMin;

    @JsonProperty("temp_max")
    private Double tempMax;

    /**
     * No args constructor for use in serialization
     */
    public Main()
    {
    }

    /**
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Double temp, Long pressure, Long humidity, Double tempMin, Double tempMax)
    {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    /**
     * @return The temp
     */
    @JsonProperty("temp")
    public Double getTemp()
    {
        return temp;
    }

    /**
     * @param temp The temp
     */
    @JsonProperty("temp")
    public void setTemp(Double temp)
    {
        this.temp = temp;
    }

    public Main withTemp(Double temp)
    {
        this.temp = temp;
        return this;
    }

    /**
     * @return The pressure
     */
    @JsonProperty("pressure")
    public Long getPressure()
    {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    @JsonProperty("pressure")
    public void setPressure(Long pressure)
    {
        this.pressure = pressure;
    }

    public Main withPressure(Long pressure)
    {
        this.pressure = pressure;
        return this;
    }

    /**
     * @return The humidity
     */
    @JsonProperty("humidity")
    public Long getHumidity()
    {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    @JsonProperty("humidity")
    public void setHumidity(Long humidity)
    {
        this.humidity = humidity;
    }

    public Main withHumidity(Long humidity)
    {
        this.humidity = humidity;
        return this;
    }

    /**
     * @return The tempMin
     */
    @JsonProperty("temp_min")
    public Double getTempMin()
    {
        return tempMin;
    }

    /**
     * @param tempMin The temp_min
     */
    @JsonProperty("temp_min")
    public void setTempMin(Double tempMin)
    {
        this.tempMin = tempMin;
    }

    public Main withTempMin(Double tempMin)
    {
        this.tempMin = tempMin;
        return this;
    }

    /**
     * @return The tempMax
     */
    @JsonProperty("temp_max")
    public Double getTempMax()
    {
        return tempMax;
    }

    /**
     * @param tempMax The temp_max
     */
    @JsonProperty("temp_max")
    public void setTempMax(Double tempMax)
    {
        this.tempMax = tempMax;
    }

    public Main withTempMax(Double tempMax)
    {
        this.tempMax = tempMax;
        return this;
    }

}
