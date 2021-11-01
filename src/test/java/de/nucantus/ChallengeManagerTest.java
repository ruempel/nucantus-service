package de.nucantus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChallengeManagerTest {
    private ChallengeManager manager;

    @BeforeEach
    public void prepare() {
        manager = ChallengeManager.getInstance();
        manager.getOpenChallenges().clear();
        manager.getAcceptedChallenges().clear();
    }

    @Test
    public void testGetOpenChallenges() {
        Challenge challenge = new Challenge();
        challenge.setPlayer1("p1");
        challenge.setSong("song");
        manager.getOpenChallenges().add(challenge);

        List<Challenge> actual = manager.getOpenChallenges();
        assertTrue(actual.contains(challenge));
    }
}
