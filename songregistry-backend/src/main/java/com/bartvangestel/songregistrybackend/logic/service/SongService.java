package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    private final ISongDAL songDAL;

    public SongService(ISongDAL songDAL) {
        this.songDAL = songDAL;
    }

    public List<SongDTO> getSongs() {
        return songDAL.getSongs();
    }

    @Override
    public List<SongDTO> getSongsByArtistName(String name) {
        return songDAL.getSongsByArtistName(name);
    }
    @Override
    public List<SongDTO> getSongsBySongTitle(String title) {
        return songDAL.getSongsBySongTitle(title);
    }

    @Override
    public SongDTO getSongById(int id) {
        return songDAL.getSongById(id);
    }

}
