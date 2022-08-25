package de.nucantus.rest

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

/**
 * Cross-origin resource sharing filter to be registered in resource configuration of application.
 */
class CORSResponseFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        val headers = responseContext.headers
        headers.add("Access-Control-Allow-Origin", "*")
        headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS")
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization")
    }
}