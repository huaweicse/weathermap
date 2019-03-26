package com.service.fusionweather;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class FusionweatherApplication
{
    public static void main(String[] args) throws Exception
    {
        Log4jUtils.init();
        BeanUtils.init();
    }
}
