package de.nucantus.rest;

import de.nucantus.model.Song;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static de.nucantus.rest.SongResource.TAG_SONGS;

/**
 * Define songs API.
 */
@Path("songs")
@Tag(name = TAG_SONGS)
public interface SongResource {
    String TAG_SONGS = "songs";

    @GET
    @Operation(summary = "List songs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Song.class)
                                    )))
            })
    Response songsGet();
}
