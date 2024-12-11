package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.dal.model.AlbumArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumArtistRepository extends JpaRepository<AlbumArtist, Integer> {
}
