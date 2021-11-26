package de.nucantus;

import de.nucantus.model.Challenge;
import de.nucantus.model.ChallengeCreator;
import de.nucantus.service.ChallengeService;
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

    @BeforeEach
    public void prepare() {
    }

    @Test
    public void testGetOpenChallenges() {
        final int testSongId = 42;
        final String testPlayerName = "Test Player Name";

        Challenge challenge = service.addChallenge(ChallengeCreator.of(testSongId, testPlayerName));
        List<Challenge> actual = service.getOpenChallenges();
        assertTrue(actual.contains(challenge));
        assertEquals(0, challenge.getId());
    }
}
