package de.nucantus.rest;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Configure the API and its path for a set of resources.
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    /**
     * Configure API classes and additional properties.
     */
    public JerseyConfig() {
        this.registerClasses(
                ChallengeResourceImpl.class,
                SongResourceImpl.class,
                CORSResponseFilter.class,
                OpenApiResource.class
        );
        this.property(ServerProperties.WADL_FEATURE_DISABLE, true);
    }
}
