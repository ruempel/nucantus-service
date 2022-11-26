package de.nucantus.rest;

import de.nucantus.service.SongService;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

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
