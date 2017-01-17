package de.nucantus;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Start Jersey server using Grizzly.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
public class Main {
    private static final String BASE_URI = "http://0.0.0.0:5026/rest/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server
     */
    private static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig();
        rc.packages(SongChallengeService.class.getPackage().getName());
        rc.register(CORSResponseFilter.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        HttpHandler staticHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(), "/");
        server.getServerConfiguration().addHttpHandler(staticHandler);
        return server;
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        //noinspection ResultOfMethodCallIgnored
        System.in.read();
        server.shutdownNow();
    }
}