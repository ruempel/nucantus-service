package de.nucantus.rest;

import de.nucantus.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 * This resource implements the song browser API.
 */
public class SongResourceImpl implements SongResource {
    private final SongService service;

    /**
     * @param service song service
     */
    @Autowired
    public SongResourceImpl(SongService service) {
        this.service = service;
    }

    @Override
    public Response songsGet() {
        return Response.ok(service.getSongs()).build();
    }
}
