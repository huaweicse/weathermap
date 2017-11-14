package com.service.forecastweatherdata.controller;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Before;
import org.junit.Test;

public class TestForecastweatherdata
{

    ForecastweatherdataImpl forecastweatherdataImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        forecastweatherdataImpl = BeanUtils.getBean("forecastweatherdataImpl");
    }

    @Test
    public void testhelloworld()
    {

    }

}