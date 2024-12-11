package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.dal.model.Review;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findBySongArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
    List<Song> findBySongNameContainingIgnoreCase(String songTitle);
    List<Song> findById(int songId);
    List<Song> findBySongArtists_Artist_Id(int artistId);
    List<Song> findByAlbum_Id(int albumId);
}
