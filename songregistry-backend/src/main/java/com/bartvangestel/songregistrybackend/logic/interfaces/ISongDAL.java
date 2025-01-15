package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;

import java.util.List;

public interface ISongDAL {
    List<SongDTO> getSongs();
    List<SongDTO> getSongsByArtistName(String name);
    List<SongDTO> getSongsBySongTitle(String title);
    SongDTO getSongById(int id);
    void addSong(SongDTO songDTO);
}
