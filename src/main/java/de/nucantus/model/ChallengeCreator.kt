package de.nucantus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * To open a new challenge, a song id and a challenging player are required.
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class ChallengeCreator {
    @NotNull
    private final int songId;
    @NotNull
    private final String challengingPlayer;
}
