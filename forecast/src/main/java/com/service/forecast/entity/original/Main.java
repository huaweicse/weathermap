package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main
{

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("grnd_level")
    private double grndLevel;

    @JsonProperty("temp_kf")
    private double tempKf;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("pressure")
    private double pressure;

    @JsonProperty("sea_level")
    private double seaLevel;

    @JsonProperty("temp_max")
    private double tempMax;

    public double getTemp()
    {
        return temp;
    }

    public void setTemp(double temp)
    {
        this.temp = temp;
    }

    public double getTempMin()
    {
        return tempMin;
    }

    public void setTempMin(double tempMin)
    {
        this.tempMin = tempMin;
    }

    public double getGrndLevel()
    {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel)
    {
        this.grndLevel = grndLevel;
    }

    public double getTempKf()
    {
        return tempKf;
    }

    public void setTempKf(double tempKf)
    {
        this.tempKf = tempKf;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public double getSeaLevel()
    {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel)
    {
        this.seaLevel = seaLevel;
    }

    public double getTempMax()
    {
        return tempMax;
    }

    public void setTempMax(double tempMax)
    {
        this.tempMax = tempMax;
    }
}