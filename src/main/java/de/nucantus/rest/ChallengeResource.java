package de.nucantus.rest;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import de.nucantus.model.ChallengeState;
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

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This interface defines the karaoke challenge API.
 */
@Path("/challenges")
@OpenAPIDefinition(
        info = @Info(
                title = "Challenge Service",
                version = "0.1.0",
                description = "Service for challenging karaoke songs and join challenges as a player"
        ),
        tags = {
                @Tag(name = ChallengeResource.TAG_CHALLENGES, description = "CRUD operations on challenges")
        },
        externalDocs = @ExternalDocumentation(description = "TODO")
)
public interface ChallengeResource {
    String TAG_CHALLENGES = "challenges";

    /**
     * Challenge a song.
     *
     * @param challenge challenge a song
     * @return open song challenge or error
     */
    @POST
    @Tag(name = TAG_CHALLENGES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Challenge a song",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Open song challenge created")
            }
    )
    Response challengesPost(
            @RequestBody(description = "Challenge containing song and player name", required = true,
                    content = @Content(
                            schema = @Schema(implementation = ChallengeCreator.class)
                    )) ChallengeCreator challenge
    );

    /**
     * List filtered challenges.
     *
     * @param state state to filter challenges
     * @return challenges matching the filter or error
     */
    @GET
    @Tag(name = TAG_CHALLENGES)
    @Operation(summary = "List challenges",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Challenge.class))))
            }
    )
    Response challengesGet(
            @Parameter(description = "filter for 'open', 'accepted' or 'all' challenges")
            @NotNull @QueryParam("state") ChallengeState state
    );

    /**
     * Join an open challenge.
     *
     * @param id            id of the challenge to join
     * @param joiningPlayer name of the joining player
     * @return accepted challenge or error
     */
    @PUT
    @Path("/{id}")
    @Tag(name = TAG_CHALLENGES)
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Join an open challenge",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Challenge joined"),
                    @ApiResponse(responseCode = "404", description = "Challenge not found")
            }
    )
    Response challengesIdPut(
            @Parameter(description = "ID of challenge to join", required = true)
            @PathParam("id") int id,
            @RequestBody(description = "Player to join the challenge", required = true,
                    content = @Content(
                            schema = @Schema(implementation = String.class))) String joiningPlayer
    );

    /**
     * Delete a challenge.
     *
     * @param id id of the challenge to join
     * @return empty response or error
     */
    @DELETE
    @Path("/{id}")
    @Tag(name = TAG_CHALLENGES)
    @Operation(summary = "Delete a challenge",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted"),
                    @ApiResponse(responseCode = "404", description = "Challenge not found")
            }
    )
    Response challengesIdDelete(
            @Parameter(description = "ID of challenge to delete", required = true)
            @PathParam("id") int id
    );
}
