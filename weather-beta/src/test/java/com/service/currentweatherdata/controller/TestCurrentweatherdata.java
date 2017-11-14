package com.service.currentweatherdata.controller;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Before;
import org.junit.Test;

public class TestCurrentweatherdata
{

    CurrentweatherdataImpl currentweatherdataImpl;

    @Before
    public void setup() throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
        currentweatherdataImpl = BeanUtils.getBean("currentweatherdataImpl");
    }

    @Test
    public void testhelloworld()
    {
    }

}