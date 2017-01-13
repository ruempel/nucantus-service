package de.nucantus;

import javax.ws.rs.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Song challenge Web service giving access to challenge data store.
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
@Path("/")
public class SongChallengeService {
    private static final Logger LOG = Logger.getLogger(SongChallengeService.class.getName());
    private ChallengeManager cm = ChallengeManager.getInstance();

    @POST
    @Path("challenge")
    @Consumes("application/x-www-form-urlencoded")
    public void challengeSong(@FormParam("song") String song, @FormParam("player") String playerName) {
        //TODO check whether song is already challenged before adding new challenge
        LOG.info("Challenge received for " + song + " from player " + playerName);
        cm.getOpenChallenges().add(new Challenge(song, playerName));
    }

    @POST
    @Path("join")
    @Consumes("application/x-www-form-urlencoded")
    public void joinSong(@FormParam("song") String song, @FormParam("player") String playerName) {
        LOG.info("Challenge accepted for " + song + " from player " + playerName);
        for (Challenge challenge : cm.getOpenChallenges()) {
            if (challenge.getSong().equals(song)) {
                cm.getAcceptedChallenges().add(new Challenge(challenge, playerName));
                cm.getOpenChallenges().remove(challenge);
                break;
            }
        }
    }

    @GET
    @Path("open")
    public List<Challenge> getOpenChallenges() {
        return cm.getOpenChallenges();
    }

    @GET
    @Path("accepted")
    public List<Challenge> getAcceptedChallenges() {
        return cm.getAcceptedChallenges();
    }

    @DELETE
    @Path("delete")
    public void deleteChallenge(@FormParam("song") String song) {
        for (Challenge challenge : cm.getAcceptedChallenges()) {
            if (song != null && challenge.getSong().equals(song)) {
                cm.getAcceptedChallenges().remove(challenge);
                break;
            }
        }
    }
}