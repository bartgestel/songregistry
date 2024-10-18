package com.bartvangestel.songregistrybackend.logic.service.interfaces;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;

import java.util.List;

public interface IArtistService {
    List<Artist> getArtists();
    List<Artist> getArtistByName(String name);
    List<Album> getAlbumsByArtistName(String name);
    List<Song> getSongsByArtistName(String name);
}
