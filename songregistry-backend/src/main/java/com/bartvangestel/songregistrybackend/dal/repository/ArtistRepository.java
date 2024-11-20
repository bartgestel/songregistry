package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.dal.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistNameContainingIgnoreCase(String name);
    Artist findById(int id);
    List<Artist> findTop6ByOrderByIdAsc();
    List<Artist> findBySongArtists_Song_Id(Integer songId);
    List<Artist> findByAlbumArtists_Album_Id(Integer albumId);
}
