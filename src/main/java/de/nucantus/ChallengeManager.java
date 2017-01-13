package de.nucantus;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory data store for challenges (does not survive JVM termination).
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-04
 */
class ChallengeManager {
    private static ChallengeManager instance;

    private List<Challenge> openChallenges = new ArrayList<>(), acceptedChallenges = new ArrayList<>();

    static ChallengeManager getInstance() {
        return instance != null ? instance : new ChallengeManager();
    }

    private ChallengeManager() {
        instance = this;
    }

    List<Challenge> getOpenChallenges() {
        return openChallenges;
    }

    List<Challenge> getAcceptedChallenges() {
        return acceptedChallenges;
    }
}
