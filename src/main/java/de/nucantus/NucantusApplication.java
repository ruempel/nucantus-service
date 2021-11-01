package de.nucantus;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Start Jersey server using Grizzly.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
@ApplicationPath("api")
public class NucantusApplication extends ResourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(NucantusApplication.class);
    private static final String APP_PATH = "api/";
    private static final String BASE_URI = "http://0.0.0.0:5026/" + APP_PATH;
    private static final String STATIC_RESOURCES_ROOT = "/";

    private NucantusApplication() {
        this.registerClasses(ChallengeResourceImpl.class, CORSResponseFilter.class, OpenApiResource.class);
        this.property(ServerProperties.WADL_FEATURE_DISABLE, true);
    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     */
    private void startServer() {
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), this);
        HttpHandler staticHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(), STATIC_RESOURCES_ROOT);
        server.getServerConfiguration().addHttpHandler(staticHandler);
    }

    public static void main(String[] args) {
        new NucantusApplication().startServer();
        LOG.info("Nucantus Jersey app started at {}", BASE_URI);
    }
}
