package de.nucantus.service

import de.nucantus.model.Song
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Get songs stored in file database.
 */
@Service
class SongService {
    /**
     * @return all available songs
     */
    val songs: List<Song>
        get() {
            val songs: MutableList<Song> = ArrayList()
            Scanner(
                Objects.requireNonNull(javaClass.classLoader.getResourceAsStream("songs.txt")),
                StandardCharsets.UTF_8
            ).use { s ->
                while (s.hasNextLine()) {
                    val song = getSongForLine(s.nextLine(), songs.size)
                    song.ifPresent { e: Song -> songs.add(e) }
                }
            }
            return songs
        }

    /**
     * Convert a database line to a song object.
     *
     * @param line input line
     * @param id   song id to assign
     * @return song for a database line or Optional.empty(), if the line cannot be parsed
     */
    private fun getSongForLine(line: String, id: Int): Optional<Song> {
        val song = line.split(" - ".toRegex(), limit = 2).toTypedArray()
        if (song.size == 2) {
            val artist = song[0].trim { it <= ' ' }
            val title = song[1].trim { it <= ' ' }
            if (artist.isNotEmpty() && title.isNotEmpty()) {
                return Optional.of(Song(id, artist, title))
            }
        }
        return Optional.empty()
    }
}