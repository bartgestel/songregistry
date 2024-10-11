package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.SongDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongDAO songDAO;

    public SongService(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public List<Song> getSongsByArtistName(String name) {
        return songDAO.findSongByArtistName(name);
    }

    public List<Song> getSongsBySongTitle(String title) {
        return songDAO.findSongByName(title);
    }
}
