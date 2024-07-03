package de.nucantus.rest;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import static de.nucantus.rest.ChallengeResource.TAG_CHALLENGES;
import static de.nucantus.rest.SongResource.TAG_SONGS;

/**
 * Configure the API and its path for a set of resources.
 */
@Component
@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "Nucantus Song Challenge Service",
                version = "0.1.0",
                description = "Service for challenging karaoke songs and join challenges as a player"
        ),
        tags = {
                @Tag(name = TAG_CHALLENGES, description = "CRUD operations on challenges"),
                @Tag(name = TAG_SONGS, description = "List songs")
        }
)
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
