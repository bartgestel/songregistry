package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.dal.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements IArtistService {
    private final IArtistDAL artistDAL;
    private final IAlbumService albumService;
    private final ISongService songService;

    @Autowired
    public ArtistService(IArtistDAL artistDAL, IAlbumService albumService, ISongService songService) {
        this.artistDAL = artistDAL;
        this.albumService = albumService;
        this.songService = songService;
    }

    public List<Artist> getArtists() {
        return artistDAL.getArtists();
    }

    public List<Artist> getArtistByName(String name) {
        return artistDAL.getArtistsByName(name);
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumService.getAlbumsByArtistName(name);
    }

    public List<Song> getSongsByArtistName(String name) {
        return songService.getSongsByArtistName(name);
    }
}
