package com.service.weather.entity.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "main",
        "description",
        "icon"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather
{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String icon;

    /**
     * No args constructor for use in serialization
     */
    public Weather()
    {
    }

    /**
     * @param id
     * @param icon
     * @param description
     * @param main
     */
    public Weather(Long id, String main, String description, String icon)
    {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Long id)
    {
        this.id = id;
    }

    public Weather withId(Long id)
    {
        this.id = id;
        return this;
    }

    /**
     * @return The main
     */
    @JsonProperty("main")
    public String getMain()
    {
        return main;
    }

    /**
     * @param main The main
     */
    @JsonProperty("main")
    public void setMain(String main)
    {
        this.main = main;
    }

    public Weather withMain(String main)
    {
        this.main = main;
        return this;
    }

    /**
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    public Weather withDescription(String description)
    {
        this.description = description;
        return this;
    }

    /**
     * @return The icon
     */
    @JsonProperty("icon")
    public String getIcon()
    {
        return icon;
    }

    /**
     * @param icon The icon
     */
    @JsonProperty("icon")
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public Weather withIcon(String icon)
    {
        this.icon = icon;
        return this;
    }

}
