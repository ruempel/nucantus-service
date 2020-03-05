package de.nucantus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Challenge for a song with a challenging player and an accepting (joining) player.
 */
public class Challenge {
    private @Valid
    String song;
    private @Valid
    String player1;
    private @Valid
    String player2;

    public Challenge song(String song) {
        this.song = song;
        return this;
    }

    @Schema(description = "song name to be challenged", required = true)
    @JsonProperty("song")
    @NotNull
    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Challenge player1(String player1) {
        this.player1 = player1;
        return this;
    }

    @Schema(description = "name of the challenging player", required = true)
    @JsonProperty("player1")
    @NotNull
    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public Challenge player2(String player2) {
        this.player2 = player2;
        return this;
    }

    @Schema(description = "name of the joining player")
    @JsonProperty("player2")
    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Challenge challenge = (Challenge) o;
        return Objects.equals(this.song, challenge.song) &&
                Objects.equals(this.player1, challenge.player1) &&
                Objects.equals(this.player2, challenge.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song, player1, player2);
    }
}
