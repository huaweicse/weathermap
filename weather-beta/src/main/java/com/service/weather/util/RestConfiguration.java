package com.service.weather.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestConfiguration.class);

	@Value("${proxy.enabled}")
	private boolean proxyEnable = false;

	@Value("${proxy.host}")
	private String proxyHost;

	@Value("${proxy.port}")
	private int proxyPort;

	@Value("${proxy.user}")
	private String proxyUser;

	@Value("${proxy.password}")
	private String proxyPassworld;

	@Value("${rest.readtimeout}")
	private int restReadTimeout;

	@Value("${rest.connecttimeout}")
	private int restConnectionTimeout;

	@Bean
	public HttpComponentsClientHttpRequestFactory httpClientFactory() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		httpRequestFactory.setConnectTimeout(restConnectionTimeout);
		httpRequestFactory.setReadTimeout(restReadTimeout);

		if (proxyEnable) {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
					new UsernamePasswordCredentials(proxyUser, proxyPassworld));
			HttpClient httpClient = HttpClientBuilder.create().setProxy(new HttpHost(proxyHost, proxyPort))
					.setDefaultCredentialsProvider(credsProvider).disableCookieManagement().build();
			httpRequestFactory.setHttpClient(httpClient);

			LOGGER.info("http proxy enabled");
		}

		return httpRequestFactory;
	}

	@Bean("restProxyTemplate")
	public RestTemplate getRestTemplate(HttpComponentsClientHttpRequestFactory httpClientFactory) {
		return new RestTemplate(httpClientFactory);
	}
}