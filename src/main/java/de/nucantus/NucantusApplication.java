package de.nucantus;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * Start Jersey server using Grizzly.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
public class NucantusApplication extends ResourceConfig {
    private static final String BASE_URI = "http://0.0.0.0:5026/rest/";
    private static final String STATIC_RESOURCES_ROOT = "/";

    private NucantusApplication() {
        this.packages(SongChallengeService.class.getPackage().getName());
        this.register(CORSResponseFilter.class);
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
        System.out.println(String.format("Nucantus Jersey app started at %s", BASE_URI));
    }
}
