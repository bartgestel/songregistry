package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("SELECT s FROM Song s JOIN s.songArtists sa JOIN sa.artist ar WHERE LOWER(ar.artistName) LIKE LOWER(CONCAT('%', :artistName, '%'))")
    List<Song> findByArtistNameContainingIgnoreCase(@Param("artistName") String artistName);
}
