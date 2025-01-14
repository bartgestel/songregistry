package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
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

    public List<ArtistDTO> getArtists() {
        return artistDAL.getArtists();
    }

    public ArtistDTO getArtistById(int id) {
        return artistDAL.getArtistById(id);
    }

    public List<ArtistDTO> getArtistByName(String name) {
        return artistDAL.getArtistsByName(name);
    }

    public List<ArtistDTO> getArtistsForHome() {
        return artistDAL.getArtistsForHome();
    }

    public List<AlbumDTO> getAlbumsByArtistName(String name) {
        return albumService.getAlbumsByArtistName(name);
    }

    public List<SongDTO> getSongsByArtistName(String name) {
        return songService.getSongsByArtistName(name);
    }

    public void addArtist(ArtistDTO artist) {
        artistDAL.addArtist(artist);
    }

}
