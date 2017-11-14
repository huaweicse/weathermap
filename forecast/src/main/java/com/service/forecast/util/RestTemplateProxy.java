package com.service.forecast.util;

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
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

/**
 * RestTemplateClient
 */
public enum RestTemplateProxy
{
    INSTANCE;

    private static final String FILE_NAME = "config/httpproxy.properties";

    private RestTemplate restTemplate = new RestTemplate();

    private RestTemplateProxy()
    {
        final Logger logger = LoggerFactory.getLogger(RestTemplateProxy.class);

        Properties prop = ConfigFileReader.readProperty(FILE_NAME);

        System.out.println(prop);

        if (!prop.isEmpty() && StringUtils.isNotBlank(prop.getProperty("USER")))
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
            factory.setConnectTimeout(5000);

            restTemplate.setRequestFactory(factory);
        }
        else
        {
            logger.info("Initialize restTemplate directly.");
        }
    }

    public RestTemplate getRestTemplate()
    {
        return restTemplate;
    }
}