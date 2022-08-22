package de.nucantus.model

/**
 * To open a new challenge, a song id and a challenging player are required.
 */
data class ChallengeCreator(val songId: Int, val challengingPlayer: String)
