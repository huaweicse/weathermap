package com.service.edge;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CookieHandler;
import org.apache.servicecomb.edge.core.AbstractEdgeDispatcher;
import org.apache.servicecomb.edge.core.EdgeInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.Map;

public class EdgeDispatcher extends AbstractEdgeDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public void init(Router router) {
        String regex = "/api/([^\\\\/]+)/(.*)";
        router.routeWithRegex(regex).handler(CookieHandler.create());
        router.routeWithRegex(regex).handler(createBodyHandler());
        router.routeWithRegex(regex).failureHandler(this::onFailure).handler(this::onRequest);
    }

    protected void onRequest(RoutingContext context) {
        Map<String, String> pathParams = context.pathParams();
        String microserviceName = pathParams.get("param0");
        String path = "/" + pathParams.get("param1");

        EdgeInvocation edgeInvocation = new EdgeInvocation();
        edgeInvocation.init(microserviceName, context, path, httpServerFilters);
        edgeInvocation.edgeInvoke();
    }

    protected void onFailure(RoutingContext context) {
        LOGGER.error("Edge service failed.", context.failure());
        HttpServerResponse response = context.response();
        response.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
        response.setStatusMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        response.end(context.failure().getMessage());
    }
}
