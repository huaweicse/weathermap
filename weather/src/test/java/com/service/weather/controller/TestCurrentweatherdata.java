package com.service.weather.controller;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Before;
import org.junit.Test;

import com.service.weather.controller.WeatherImpl;

public class TestCurrentweatherdata
{

    WeatherImpl currentweatherdataImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        currentweatherdataImpl = BeanUtils.getBean("weatherImpl");
    }

    @Test
    public void testhelloworld()
    {
    }

}