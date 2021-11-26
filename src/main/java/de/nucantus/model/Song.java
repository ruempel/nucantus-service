package de.nucantus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Represent a song from the song database.
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class Song {
    @NotNull
    private final Integer id;
    @NotNull
    private final String artist;
    @NotNull
    private final String title;
}
