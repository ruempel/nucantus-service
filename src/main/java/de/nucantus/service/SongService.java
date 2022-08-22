package de.nucantus.service;

import de.nucantus.model.Song;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Get songs stored in file database.
 */
@Service
public class SongService {
    /**
     * @return all available songs
     */
    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        try (Scanner s = new Scanner(Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream("songs.txt")), StandardCharsets.UTF_8)) {
            while (s.hasNextLine()) {
                Optional<Song> song = getSongForLine(s.nextLine(), songs.size());
                song.ifPresent(songs::add);
            }
        }
        return songs;
    }

    /**
     * Convert a database line to a song object.
     *
     * @param line input line
     * @param id   song id to assign
     * @return song for a database line or Optional.empty(), if the line cannot be parsed
     */
    public Optional<Song> getSongForLine(String line, int id) {
        String[] song = line.split(" - ", 2);
        if (song.length == 2) {
            String artist = song[0].trim();
            String title = song[1].trim();
            if (!artist.isEmpty() && !title.isEmpty()) {
                return Optional.of(new Song(id, artist, title));
            }
        }
        return Optional.empty();
    }
}
