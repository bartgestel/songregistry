package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.model.Song;

import java.util.List;

public interface ISongDAL {
    List<SongDTO> getSongs();
    List<SongDTO> getSongsByArtistName(String name);
    List<SongDTO> getSongsBySongTitle(String title);
    SongDTO getSongById(int id);
}
