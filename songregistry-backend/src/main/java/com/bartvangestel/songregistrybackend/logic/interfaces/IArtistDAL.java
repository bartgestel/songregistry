package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IArtistDAL {
    List<Artist> getArtists();
    List<Artist> getArtistsByName(String name);
}
