package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain
{

    @JsonProperty("3h")
    private double jsonMember3h;

    public void setJsonMember3h(double jsonMember3h)
    {
        this.jsonMember3h = jsonMember3h;
    }

    public double getJsonMember3h()
    {
        return jsonMember3h;
    }
}