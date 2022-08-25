package de.nucantus.service

import de.nucantus.model.Challenge
import de.nucantus.model.ChallengeCreator
import org.springframework.stereotype.Service

/**
 * Store challenge data in-memory (does not survive JVM termination).
 */
@Service
class ChallengeService {
    private val challenges: MutableList<Challenge> = ArrayList()
    val openChallenges: List<Challenge> get() = challenges.filter(Challenge::isOpen)
    val acceptedChallenges: List<Challenge> get() = challenges.filterNot(Challenge::isOpen)
    val allChallenges: List<Challenge> get() = challenges

    fun addChallenge(challengeCreator: ChallengeCreator): Challenge {
        val id = (challenges.maxOfOrNull { it.id } ?: -1) + 1 // generate unused challenge id
        val challenge = Challenge(id, challengeCreator.songId, challengeCreator.challengingPlayer, null)
        challenges.add(challenge)
        return challenge
    }

    fun joinChallenge(challengeId: Int, joiningPlayer: String): Challenge {
        // check existence
        val challengeToJoin = challenges.first { (id): Challenge -> id == challengeId }

        // add joining player and thus update database
        challengeToJoin.joiningPlayer = joiningPlayer
        return challengeToJoin
    }

    fun deleteChallenge(challengeId: Int): Boolean {
        return challenges.removeIf { (id): Challenge -> id == challengeId }
    }

    fun deleteAllChallenges() {
        challenges.clear()
    }
}