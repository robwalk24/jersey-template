package io.rowtech.api.filter;

import com.google.common.collect.ImmutableSet;
import com.google.common.net.HttpHeaders;

import javax.annotation.Priority;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Priority(Priorities.AUTHENTICATION)
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {
    public static final int ACCESS_CONTROL_MAX_AGE_SECONDS = 60 * 60;

    public static final Set<String> METHODS_ALLOWED = ImmutableSet.of(
            HttpMethod.GET);

    public static final Set<String> HEADERS_ALLOWED = ImmutableSet.of(
        HttpHeaders.ORIGIN
    );

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // TODO implement check
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();

        switch (getRequestType(requestContext)){
             case PREFLIGHT:
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, true);
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HEADERS_ALLOWED.stream().collect(joining(",")));
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, METHODS_ALLOWED.stream().collect(joining(",")));
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, ACCESS_CONTROL_MAX_AGE_SECONDS);
                 responseHeaders.add(HttpHeaders.CONTENT_LENGTH, 0);
                 break;
             case CROSS_DOMAIN:
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                 responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, true);
                 break;
         }
    }

    public CorsRequestType getRequestType(ContainerRequestContext requestContext){
        if (HttpMethod.OPTIONS.equalsIgnoreCase(requestContext.getMethod())){
            return CorsRequestType.PREFLIGHT;
        }

        if (requestContext.getHeaders().containsKey(HttpHeaders.ORIGIN)){
            return CorsRequestType.CROSS_DOMAIN;
        }

        return CorsRequestType.SAME_ORIGIN;
    }
}
