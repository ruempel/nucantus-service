package de.nucantus;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

public class ChallengeResourceImpl implements ChallengeResource {
    private static final Logger LOG = Logger.getLogger(ChallengeResourceImpl.class.getName());
    private ChallengeManager cm = ChallengeManager.getInstance();

    private enum ChallengeState {
        OPEN,
        ACCEPTED,
        ALL
    }

    @Override
    public Response challengesPost(Challenge challenge) {
        LOG.info("Challenge received for " + challenge.getSong() + " from player " + challenge.getPlayer1());
        if (cm.getAllChallenges().stream().noneMatch(challenge::equals)) {
            cm.getOpenChallenges().add(challenge);
            return Response.status(201, "Challenge created").build();
        }
        return Response.status(409, "Song already challenged").build();
    }

    @Override
    public Response challengesGet(String state) {
        LOG.info("Request challenges with state " + state);

        ChallengeState validatedState;
        try { // parse state
            validatedState = ChallengeState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException exception) {
            return Response.status(400, "Bad request: unknown filter option").build();
        }

        switch (validatedState) {
            case OPEN:
                return Response.ok().entity(cm.getOpenChallenges()).build();
            case ACCEPTED:
                return Response.ok().entity(cm.getAcceptedChallenges()).build();
            case ALL:
                return Response.ok().entity(cm.getAllChallenges()).build();
        }
        return Response.serverError().build();
    }

    @Override
    public Response challengesIdPut(String id, String playerName) {
        LOG.info("Challenge accepted for " + id + " from player " + playerName);
        Optional<Challenge> challenge = cm.getOpenChallenges().stream()
                .filter(c -> c.getSong().equals(id)).findFirst();
        if (challenge.isPresent()) {
            Challenge challengeToJoin = challenge.get();
            challengeToJoin.setPlayer2(playerName);
            cm.getAcceptedChallenges().add(challengeToJoin);
            cm.getOpenChallenges().remove(challenge.get());
            return Response.status(204, "Challenged joined").build();
        }
        return Response.status(404, "Challenge not found").build();
    }

    @Override
    public Response challengesIdDelete(String id) {
        try {
            String decodedSong = URLDecoder.decode(id, StandardCharsets.UTF_8.name());
            Optional<Challenge> challenge = cm.getAcceptedChallenges().stream()
                    .filter(c -> c.getSong().equals(decodedSong)).findFirst();
            if (challenge.isPresent()) {
                LOG.info("Delete song from accepted list: " + decodedSong);
                cm.getAcceptedChallenges().remove(challenge.get());
                return Response.noContent().build();
            }
            return Response.status(404, "Challenge not found").build();
        } catch (UnsupportedEncodingException e) {
            return Response.status(500, e.getMessage()).build();
        }
    }
}
