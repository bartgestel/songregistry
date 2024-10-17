package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.service.interfaces.IArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements IArtistService {
    public final ArtistRepository artistRepository;
    private final AlbumServiceImpl albumServiceImpl;
    private final SongServiceImpl songServiceImpl;

    public ArtistServiceImpl(ArtistRepository artistRepository, AlbumServiceImpl albumServiceImpl, SongServiceImpl songServiceImpl) {
        this.artistRepository = artistRepository;
        this.albumServiceImpl = albumServiceImpl;
        this.songServiceImpl = songServiceImpl;
    }

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    public List<Artist> getArtistByName(String name) {
        return artistRepository.findByArtistNameContainingIgnoreCase(name);
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumServiceImpl.getAlbumsByArtistName(name);
    }

    public List<Song> getSongsByArtistName(String name) {
        return songServiceImpl.getSongsByArtistName(name);
    }
}
