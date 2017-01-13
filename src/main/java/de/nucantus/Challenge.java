package de.nucantus;

/**
 * Song challenge including the song to be challenged and player names (only firstPlayer for open challenges).
 *
 * @author Andreas Rümpel <ruempel@gmail.com>
 * @since 2017-01-03
 */
class Challenge {
    private String song, firstPlayer, secondPlayer;

    Challenge(String song, String playerName) {
        this.song = song;
        this.firstPlayer = playerName;
    }

    Challenge(Challenge openChallenge, String playerName) {
        this.song = openChallenge.song;
        this.firstPlayer = openChallenge.firstPlayer;
        this.secondPlayer = playerName;
    }

    String getSong() {
        return song;
    }

    String getFirstPlayer() {
        return firstPlayer;
    }

    String getSecondPlayer() {
        return secondPlayer;
    }
}