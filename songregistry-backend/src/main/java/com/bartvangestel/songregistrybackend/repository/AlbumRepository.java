package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("SELECT a FROM Album a JOIN a.albumArtists aa JOIN aa.artist ar WHERE LOWER(ar.artistName) LIKE LOWER(CONCAT('%', :artistName, '%'))")
    List<Album> findByAlbumArtistsContainingIgnoreCase(@Param("artistName") String artistName);
}
