package de.nucantus.model

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * A challenge contains a song with a challenging player. If the challenge is accepted, it contains a second player.
 */
data class Challenge(
    val id: Int,
    val songId: Int,
    val challengingPlayer: String,
    var joiningPlayer: String?
) {
    /**
     * @return whether the challenge is open for players to join
     */
    @get:JsonIgnore
    val isOpen get() = joiningPlayer == null
}
