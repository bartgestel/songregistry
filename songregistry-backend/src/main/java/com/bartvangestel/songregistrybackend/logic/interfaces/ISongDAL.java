package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.dal.model.Song;

import java.util.List;

public interface ISongDAL {
    List<Song> getSongs();
    List<Song> getSongsByArtistName(String name);
    List<Song> getSongsBySongTitle(String title);
}
