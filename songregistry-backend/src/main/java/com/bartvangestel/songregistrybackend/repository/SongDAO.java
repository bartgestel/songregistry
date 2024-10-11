package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.interfaces.SongRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongDAO {
    private final SongRepository songRepository;

    public SongDAO(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findSongByName(String songTitle) {
        return songRepository.findBySongNameContainingIgnoreCase(songTitle);
    }

    public List<Song> findSongByArtistName(String artistName) {
        return songRepository.findBySongArtists_Artist_ArtistNameContainingIgnoreCase(artistName);
    }
}
