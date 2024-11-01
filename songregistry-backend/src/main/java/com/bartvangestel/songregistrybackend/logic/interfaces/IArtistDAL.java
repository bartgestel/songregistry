package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.dal.model.Artist;

import java.util.List;

public interface IArtistDAL {
    List<Artist> getArtists();
    List<Artist> getArtistsByName(String name);
    List<Artist> getArtistsForHome();
    List<Artist> getArtistById(int id);
}
