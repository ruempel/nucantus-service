package de.nucantus;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/challenges")
@OpenAPIDefinition(
        info = @Info(
                title = "Challenge Service",
                version = "0.1.0",
                description = "Service for challenging karaoke songs and join challenges as a player"
        ),
        tags = {
                @Tag(name = "challenges", description = "CRUD operations on challenges")
        },
        externalDocs = @ExternalDocumentation(description = "TODO")
)
public interface ChallengeResource {
    @POST
    @Tag(name = "challenges")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Challenge a song",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Song challenged"),
                    @ApiResponse(responseCode = "409", description = "Song already challenged"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    Response challengesPost(@RequestBody(description = "Challenge containing song and player name", required = true,
            content = @Content(
                    schema = @Schema(implementation = Challenge.class)
            )) Challenge challenge);

    @GET
    @Tag(name = "challenges")
    @Operation(summary = "List challenges",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Challenge.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad request: unknown filter option"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    Response challengesGet(@Parameter(description = "filter for 'open', 'accepted' or 'all' challenges")
                           @QueryParam("state") String state);
    // TODO define possible values as enum

    @PUT
    @Path("/{id}")
    @Tag(name = "challenges")
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(summary = "Join an open challenge",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Challenge joined"),
                    @ApiResponse(responseCode = "404", description = "Challenge not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    Response challengesIdPut(@Parameter(description = "Challenge to join", required = true)
                             @PathParam("id") String id,
                             @RequestBody(description = "Player to join the challenge", required = true,
                                     content = @Content(
                                             schema = @Schema(implementation = String.class))) String playerName);

    @DELETE
    @Path("/{id}")
    @Tag(name = "challenges")
    @Operation(summary = "Delete a challenge",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted"),
                    @ApiResponse(responseCode = "404", description = "Challenge not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    Response challengesIdDelete(@Parameter(description = "Challenge id to delete", required = true)
                                @PathParam("id") String id);
}
