package com.service.forecast.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateClient
 */
public enum RestTemplateProxy
{
    INSTANCE;

    private static final String FILE_NAME = "httpproxy.properties";
    
    private static final int TMOUT= 10000;

    @Autowired
    private RestTemplate restTemplate;
    
    private RestTemplateProxy()
    {
        final Logger logger = LoggerFactory.getLogger(RestTemplateProxy.class);

        File file = new File(FILE_NAME);
        
        Properties prop = null;
        
        if (file.exists())
        {
        	 prop = ConfigFileReader.readProperty(FILE_NAME);
        }

        if (prop != null && !prop.isEmpty() && StringUtils.isNotBlank(prop.getProperty("USER")))
        {
            logger.info("Initialize restTemplate with http proxy.");
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(prop.getProperty("HOST"), Integer.valueOf(prop.getProperty("PORT"))),
                                         new UsernamePasswordCredentials(prop.getProperty("USER"), prop.getProperty("PWD")));
            HttpClient httpClient = HttpClientBuilder.create()
                    .setProxy(new HttpHost(prop.getProperty("HOST"), Integer.valueOf(prop.getProperty("PORT"))))
                    .setDefaultCredentialsProvider(credsProvider)
                    .disableCookieManagement()
                    .build();
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setHttpClient(httpClient);
            factory.setConnectTimeout(TMOUT);

            restTemplate.setRequestFactory(factory);
        }
        else
        {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(TMOUT);

			restTemplate.setRequestFactory(factory);        	
            logger.info("Initialize restTemplate directly.");
        }
    }

    public RestTemplate getRestTemplate()
    {
        return restTemplate;
    }
}