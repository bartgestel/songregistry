package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    private final ISongDAL songDAL;

    public SongService(ISongDAL songDAL) {
        this.songDAL = songDAL;
    }

    public List<Song> getSongs() {
        return songDAL.getSongs();
    }

    @Override
    public List<Song> getSongsByArtistName(String name) {
        return songDAL.getSongsByArtistName(name);
    }
    @Override
    public List<Song> getSongsBySongTitle(String title) {
        return songDAL.getSongsBySongTitle(title);
    }
}
