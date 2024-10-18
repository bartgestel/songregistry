package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findBySongArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
    List<Song> findBySongNameContainingIgnoreCase(String songTitle);
}
