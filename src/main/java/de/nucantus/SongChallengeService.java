package de.nucantus;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Song challenge Web service giving access to challenge data store.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
@Path("challenges")
public class SongChallengeService {
    private static final Logger LOG = Logger.getLogger(SongChallengeService.class.getName());
    private ChallengeManager cm = ChallengeManager.getInstance();

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void challengeSong(@FormParam("song") String song, @FormParam("player") String playerName) {
        //TODO check whether song is already challenged before adding new challenge
        LOG.info("Challenge received for " + song + " from player " + playerName);
        cm.getOpenChallenges().add(new Challenge(song, playerName));
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public void joinSong(@FormParam("song") String song, @FormParam("player") String playerName) {
        LOG.info("Challenge accepted for " + song + " from player " + playerName);
        Optional<Challenge> challenge = cm.getOpenChallenges().stream()
                .filter(c -> c.getSong().equals(song)).findFirst();
        if (challenge.isPresent()) {
            cm.getAcceptedChallenges().add(new Challenge(challenge.get(), playerName));
            cm.getOpenChallenges().remove(challenge.get());
        } else {
            // TODO no open challenge to join
        }
    }

    @GET
    public List<Challenge> getChallenges(@QueryParam("open") String open) {
        return "true".equalsIgnoreCase(open) ? cm.getOpenChallenges() : cm.getAcceptedChallenges();
    }

    @DELETE
    @Path("/{song}")
    public void deleteChallenge(@PathParam("song") String song) throws UnsupportedEncodingException {
        String decodedSong = URLDecoder.decode(song, StandardCharsets.UTF_8.name());
        Optional<Challenge> challenge = cm.getAcceptedChallenges().stream()
                .filter(c -> c.getSong().equals(decodedSong)).findFirst();
        if (challenge.isPresent()) {
            LOG.info("Delete song from accepted list: " + decodedSong);
            cm.getAcceptedChallenges().remove(challenge.get());
        }
    }
}
