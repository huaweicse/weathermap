package com.service.fusionweatherdata.controller;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Before;
import org.junit.Test;

public class TestFusionweatherdata
{

    FusionweatherdataImpl fusionweatherdataImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        fusionweatherdataImpl = BeanUtils.getBean("fusionweatherdataImpl");
    }

    @Test
    public void testhelloworld()
    {

    }

}