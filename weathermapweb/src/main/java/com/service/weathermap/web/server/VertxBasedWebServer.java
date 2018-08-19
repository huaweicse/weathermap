package com.service.weathermap.web.server;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class VertxBasedWebServer {

    @Value("${app.port:3000}")
    int port;

    @Autowired
    Map<String,Handler> handlers;

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        Router router = createRouter(vertx);
        startServer(vertx, router);
    }

    private Router createRouter(Vertx vertx) {
        Router router = Router.router(vertx);
        handlers.forEach((path, handler)->{
            router.route(path).handler(handler);

        });
        return router;
    }

    private void startServer(Vertx vertx, Router router) {
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(port);
    }

}
