package de.nucantus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * In-memory data store for challenges (does not survive JVM termination).
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-04
 */
class ChallengeManager {
    private static ChallengeManager instance;

    private List<Challenge> openChallenges = new ArrayList<>();
    private List<Challenge> acceptedChallenges = new ArrayList<>();

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

    List<Challenge> getAllChallenges() {
        return Stream.of(this.getOpenChallenges(), this.getAcceptedChallenges())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}
