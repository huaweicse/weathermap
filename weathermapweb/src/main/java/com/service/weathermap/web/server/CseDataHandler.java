package com.service.weathermap.web.server;

import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Component("/weathermapweb/ui/fusionweatherdata")
@Profile("!mesher")
public class CseDataHandler implements Handler<RoutingContext>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MesherDataHandler.class);

    private static final RestTemplate invoker = RestTemplateBuilder.create();

    @Value("${app.fusionweather.baseUrl:cse://fusionweather/fusionweather/show}")
    String baseUrl;

    @Override
    public void handle(RoutingContext routingContext) {
        String data = retrieveData(
                routingContext.request().getParam("city")
                , routingContext.request().getParam("user")
        );

        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .end(data);
    }


    private String retrieveData(String city, String user) {
        String url = baseUrl+"?city=" + city;
        if (!StringUtils.isEmpty(user)) {
            url = url + "&user=" + user;
        }
        LOGGER.info("GETing {}", url);
        return Json.encode(invoker.getForObject(url, String.class));
    }
}
