package com.service.fusionweather.controller;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Before;
import org.junit.Test;

import com.service.fusionweather.controller.FusionweatherImpl;

public class TestFusionweatherdata
{

    FusionweatherImpl fusionweatherdataImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        fusionweatherdataImpl = BeanUtils.getBean("fusionweatherImpl");
    }

    @Test
    public void testhelloworld()
    {

    }

}