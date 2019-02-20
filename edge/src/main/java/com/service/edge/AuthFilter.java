package com.service.edge;

import org.apache.commons.lang.StringUtils;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.common.http.HttpStatus;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilter implements HttpServerFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
        LOGGER.info("Edge service has received a request.");
        if (StringUtils.isBlank(requestEx.getHeader("auth"))) {
            LOGGER.error("Auth header is needed.");
            return Response.failResp(ExceptionFactory.create(new HttpStatus(401, "auth: weather."),
                    new CommonExceptionData("Auth header is needed (auth: weather).")));
        }
        return null;
    }

    @Override
    public void beforeSendResponse(Invocation invocation, HttpServletResponseEx responseEx) {
    }
}
