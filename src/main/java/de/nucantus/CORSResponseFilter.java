package de.nucantus;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;

/**
 * Cross-origin resource sharing filter to be registered in resource configuration of application.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @see org.glassfish.jersey.server.ResourceConfig
 * @since 2017-01-04
 */
public class CORSResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
    }
}
