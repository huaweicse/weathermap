package com.service.forecast.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * read config file
 */
public final class ConfigFileReader
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);

    private ConfigFileReader()
    {
    }

    public static Properties readProperty(String fileName)
    {
        Properties prop = new Properties();
        LOGGER.debug("Ready to read property file.");
        InputStream inputStream = null;
        try
        {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
            prop.load(inputStream);
            inputStream.close();
            return prop;
        }
        catch (Exception e)
        {
            LOGGER.error("Read property file failed, IOException");
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
        return prop;
    }
}
