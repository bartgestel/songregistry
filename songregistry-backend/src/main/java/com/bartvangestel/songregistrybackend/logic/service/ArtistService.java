package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Artist> artists = artistDAL.getArtists();
        return artists.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ArtistDTO getArtistById(int id) {
        List<Artist> artists = artistDAL.getArtistById(id);
        return artists.stream().map(this::convertToDTOWithRelations).findFirst().orElse(null);
    }

    public List<ArtistDTO> getArtistByName(String name) {
        List<Artist> artists = artistDAL.getArtistsByName(name);
        return artists.stream().map(this::convertToDTOWithRelations).collect(Collectors.toList());
    }

    public List<ArtistDTO> getArtistsForHome() {
        List<Artist> artists = artistDAL.getArtistsForHome();
        return artists.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtistName(String name) {
        return albumService.getAlbumsByArtistName(name);
    }

    public List<SongDTO> getSongsByArtistName(String name) {
        return songService.getSongsByArtistName(name);
    }

    private ArtistDTO convertToDTO(Artist artist){
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setArtistName(artist.getArtistName());
        return artistDTO;
    }

    private ArtistDTO convertToDTOWithRelations(Artist artist){
        ArtistDTO artistDTO = convertToDTO(artist);
        artistDTO.setArtistAlbums(albumService.getAlbumsByArtistName(artist.getArtistName()));
        artistDTO.setArtistSongs(songService.getSongsByArtistName(artist.getArtistName()));
        return artistDTO;
    }
}
