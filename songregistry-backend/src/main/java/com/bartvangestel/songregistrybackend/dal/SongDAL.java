package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAL implements ISongDAL {
    public final SongRepository songRepository;

    public SongDAL(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public List<Song> getSongsByArtistName(String name) {
        return songRepository.findBySongArtists_Artist_ArtistNameContainingIgnoreCase(name);
    }

    public List<Song> getSongsBySongTitle(String title) {
        return songRepository.findBySongNameContainingIgnoreCase(title);
    }
}
