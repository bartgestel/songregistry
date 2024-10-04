package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    public final ArtistRepository artistRepository;
    private final AlbumService albumService;
    private final SongService songService;

    public ArtistService(ArtistRepository artistRepository, AlbumService albumService, SongService songService) {
        this.artistRepository = artistRepository;
        this.albumService = albumService;
        this.songService = songService;
    }

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    public List<Artist> getArtistByName(String name) {
        return artistRepository.findByArtistNameContainingIgnoreCase(name);
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumService.getAlbumsByArtistName(name);
    }

    public List<Song> getSongsByArtistName(String name) {
        return songService.getSongsByArtistName(name);
    }
}
