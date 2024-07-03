package de.nucantus.rest;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import de.nucantus.model.ChallengeState;
import de.nucantus.service.ChallengeService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class ChallengeResourceImpl implements ChallengeResource {
    private final ChallengeService service;
    @Context
    private UriInfo uriInfo;

    public ChallengeResourceImpl(ChallengeService service) {
        this.service = service;
    }

    @Override
    public Response challengesPost(ChallengeCreator challengeCreator) {
        log.info("Challenge received for " + challengeCreator.getSongId()
                + " from player " + challengeCreator.getChallengingPlayer());

        Challenge challenge = service.addChallenge(challengeCreator);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(challenge.getId()));
        return Response.created(uriBuilder.build()).build();

        // return Response.status(Response.Status.CONFLICT.getStatusCode(), "Song already challenged").build();
    }

    @Override
    public Response challengesGet(ChallengeState state) {
        log.info("Request challenges with state " + state);

        return switch (state) {
            case OPEN -> Response.ok().entity(service.getOpenChallenges()).build();
            case ACCEPTED -> Response.ok().entity(service.getAcceptedChallenges()).build();
            case ALL -> Response.ok().entity(service.getAllChallenges()).build();
        };
    }

    @Override
    public Response challengesIdPut(int id, String joiningPlayer) {
        log.info("Challenge accepted for " + id + " from player " + joiningPlayer);

        try {
            Challenge challenge = service.joinChallenge(id, joiningPlayer);
            return Response.ok(challenge).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response challengesIdDelete(int id) {
        if (service.deleteChallenge(id)) {
            log.info("Deleted song from accepted list: " + id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
