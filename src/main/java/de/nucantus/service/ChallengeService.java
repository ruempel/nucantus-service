package de.nucantus.service;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Store challenge data in-memory (does not survive JVM termination).
 */
@Service
public class ChallengeService {
    private final List<Challenge> challenges = new ArrayList<>();

    public List<Challenge> getOpenChallenges() {
        return challenges.stream().filter(Challenge::isOpen).toList();
    }

    public List<Challenge> getAcceptedChallenges() {
        return challenges.stream().filter(Predicate.not(Challenge::isOpen)).toList();
    }

    public List<Challenge> getAllChallenges() {
        return challenges.stream().toList();
    }

    public Challenge addChallenge(ChallengeCreator challengeCreator) {
        // generate unused challenge id
        int id = challenges.stream()
                .map(Challenge::getId)
                .max(Comparator.naturalOrder())
                .orElse(-1) + 1;
        Challenge challenge = Challenge.of(id, challengeCreator.getSongId(),
                challengeCreator.getChallengingPlayer()
        );
        challenges.add(challenge);
        return challenge;
    }

    public Challenge joinChallenge(int challengeId, String joiningPlayer) {
        // check existence
        Challenge challengeToJoin = challenges.stream()
                .filter(challenge -> challenge.getId() == challengeId)
                .findFirst()
                .orElseThrow();

        // add joining player and thus update database
        challengeToJoin.setJoiningPlayer(joiningPlayer);
        return challengeToJoin;
    }

    public boolean deleteChallenge(int challengeId) {
        return challenges.removeIf(challenge -> challenge.getId() == challengeId);
    }
}
