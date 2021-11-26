package de.nucantus.rest;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import de.nucantus.model.ChallengeState;
import de.nucantus.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.NoSuchElementException;

/**
 * This resource implements the challenge management API.
 */
@Component
public class ChallengeResourceImpl implements ChallengeResource {
    private static final Logger LOG = LoggerFactory.getLogger(ChallengeResourceImpl.class);

    private final ChallengeService service;
    @Context
    private UriInfo uriInfo;

    /**
     * @param service challenge service
     */
    @Autowired
    public ChallengeResourceImpl(ChallengeService service) {
        this.service = service;
    }

    @Override
    public Response challengesPost(ChallengeCreator challengeCreator) {
        LOG.info("Challenge received for " + challengeCreator.getSongId()
                + " from player " + challengeCreator.getChallengingPlayer());

        Challenge challenge = service.addChallenge(challengeCreator);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(challenge.getId()));
        return Response.created(uriBuilder.build()).build();

        // return Response.status(Response.Status.CONFLICT.getStatusCode(), "Song already challenged").build();
    }

    @Override
    public Response challengesGet(ChallengeState state) {
        LOG.info("Request challenges with state " + state);

        return switch (state) {
            case OPEN -> Response.ok().entity(service.getOpenChallenges()).build();
            case ACCEPTED -> Response.ok().entity(service.getAcceptedChallenges()).build();
            case ALL -> Response.ok().entity(service.getAllChallenges()).build();
        };
    }

    @Override
    public Response challengesIdPut(int id, String joiningPlayer) {
        LOG.info("Challenge accepted for " + id + " from player " + joiningPlayer);

        try {
            Challenge challenge = service.joinChallenge(id, joiningPlayer);
            return Response.ok(challenge).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Challenge not found").build();
        }
    }

    @Override
    public Response challengesIdDelete(int id) {
        if (service.deleteChallenge(id)) {
            LOG.info("Deleted song from accepted list: " + id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Challenge not found").build();
        }
    }
}
