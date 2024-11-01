package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.dal.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByAlbumNameContainingIgnoreCase(String albumTitle);
    List<Album> findById(int albumId);
    List<Album> findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
    List<Album> findTop6ByOrderByIdAsc();
}
