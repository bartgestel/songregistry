package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> getSongsByArtistName(String name);
    List<Song> getSongsBySongTitle(String title);
}
