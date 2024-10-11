package com.bartvangestel.songregistrybackend.repository.interfaces;

import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findBySongArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
    List<Song> findBySongNameContainingIgnoreCase(String songTitle);
}
