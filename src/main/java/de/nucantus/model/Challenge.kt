package de.nucantus.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.validation.constraints.NotNull

/**
 * A challenge contains a song with a challenging player. If the challenge is accepted, it contains a second player.
 */
data class Challenge(
    val id: @NotNull Int,
    val songId: Int,
    val challengingPlayer: String,
    var joiningPlayer: String?
) {
    /**
     * @return whether the challenge is open for players to join
     */
    @get:JsonIgnore
    val isOpen: Boolean
        get() = joiningPlayer == null
}
