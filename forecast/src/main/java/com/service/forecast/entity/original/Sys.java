package com.service.forecast.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys
{

    @JsonProperty("pod")
    private String pod;

    public String getPod()
    {
        return pod;
    }

    public void setPod(String pod)
    {
        this.pod = pod;
    }
}