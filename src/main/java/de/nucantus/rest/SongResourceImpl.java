package de.nucantus.rest;

import de.nucantus.service.SongService;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class SongResourceImpl implements SongResource {
    private final SongService service;

    public SongResourceImpl(SongService service) {
        this.service = service;
    }

    @Override
    public Response songsGet() {
        return Response.ok(service.getSongs()).build();
    }
}
