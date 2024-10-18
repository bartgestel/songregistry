package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ISongDAL {
    List<Song> getSongs();
    List<Song> getSongsByArtistName(String name);
    List<Song> getSongsBySongTitle(String title);
}
