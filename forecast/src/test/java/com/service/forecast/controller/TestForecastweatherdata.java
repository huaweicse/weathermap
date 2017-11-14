package com.service.forecast.controller;

import org.junit.Before;
import org.junit.Test;

import com.service.forecast.controller.ForecastImpl;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;

public class TestForecastweatherdata
{

    ForecastImpl forecastImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        forecastImpl = BeanUtils.getBean("forecastImpl");
    }

    @Test
    public void testhelloworld()
    {

    }

}