package de.nucantus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * A challenge contains a song with a challenging player. If the challenge is accepted, it contains a second player.
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class Challenge {
    @NotNull
    private final int id;
    @NotNull
    private final int songId;
    @NotNull
    private final String challengingPlayer;
    private String joiningPlayer;

    /**
     * @return whether the challenge is open for players to join
     */
    @JsonIgnore
    public boolean isOpen() {
        return joiningPlayer == null;
    }
}
