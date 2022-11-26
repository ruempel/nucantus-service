package de.nucantus.rest;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import de.nucantus.model.ChallengeState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static de.nucantus.rest.ChallengeResource.TAG_CHALLENGES;

/**
 * Define karaoke challenge API.
 */
@Path("challenges")
@Tag(name = TAG_CHALLENGES)
public interface ChallengeResource {
    String TAG_CHALLENGES = "challenges";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Challenge a song",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Open song challenge created")
            }
    )
    Response challengesPost(
            @RequestBody(description = "Challenge containing song and player name", required = true) ChallengeCreator challenge
    );

    @GET
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

    @PUT
    @Path("{id}")
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
            @RequestBody(description = "Player to join the challenge", required = true) String joiningPlayer
    );

    @DELETE
    @Path("{id}")
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
