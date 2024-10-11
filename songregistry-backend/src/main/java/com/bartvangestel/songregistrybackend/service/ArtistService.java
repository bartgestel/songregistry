package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.ArtistDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    public final ArtistDAO artistDAO;
    private final AlbumService albumService;
    private final SongService songService;

    public ArtistService(ArtistDAO artistDAO, AlbumService albumService, SongService songService) {
        this.artistDAO = artistDAO;
        this.albumService = albumService;
        this.songService = songService;
    }

    public List<Artist> getArtists() {
        return artistDAO.findAll();
    }

    public List<Artist> getArtistByName(String name) {
        return artistDAO.findArtistByName(name);
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumService.getAlbumsByArtistName(name);
    }

    public List<Song> getSongsByArtistName(String name) {
        return songService.getSongsByArtistName(name);
    }
}
