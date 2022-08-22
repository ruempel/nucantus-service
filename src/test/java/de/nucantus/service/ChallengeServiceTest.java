package de.nucantus.service;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ChallengeServiceTest {
    @Autowired
    private ChallengeService service;

    private final ChallengeCreator challenge = new ChallengeCreator(42, "P1");

    @BeforeEach
    public void prepare() {
        service.deleteAllChallenges();
        service.addChallenge(challenge);
    }

    @Test
    public void testGetOpenChallenges() {
        List<Challenge> actual = service.getOpenChallenges();
        assertEquals(1, actual.size());
        assertEquals(challenge.getSongId(), actual.get(0).getSongId());
        assertEquals(challenge.getChallengingPlayer(), actual.get(0).getChallengingPlayer());
    }

    @Test
    public void testJoinChallengeAndGetAcceptedChallenges() {
        String joiningPlayerName = "P2";
        Challenge challengeToAccept = service.addChallenge(challenge);
        service.joinChallenge(challengeToAccept.getId(), joiningPlayerName);
        List<Challenge> actual = service.getAcceptedChallenges();
        assertEquals(1, actual.size());
        assertEquals(joiningPlayerName, actual.get(0).getJoiningPlayer());
    }

    @Test
    public void testGetAllChallenges() {
        service.addChallenge(challenge);
        List<Challenge> actual = service.getAllChallenges();
        assertEquals(2, actual.size());
    }

    @Test
    public void testDeleteChallenge() {
        int challengeToDeleteId = service.getAllChallenges().get(0).getId();
        boolean result = service.deleteChallenge(challengeToDeleteId);
        assertTrue(result);
    }
}
