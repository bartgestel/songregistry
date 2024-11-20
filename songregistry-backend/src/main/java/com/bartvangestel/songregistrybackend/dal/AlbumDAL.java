package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.dal.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumDAL implements IAlbumDAL {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumDAL(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public List<AlbumDTO> getAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtistName(String artistName) {
        List<Album> albums = albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(artistName);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByAlbumTitle(String albumTitle) {
        List<Album> albums = albumRepository.findByAlbumNameContainingIgnoreCase(albumTitle);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsForHome() {
        List<Album> albums = albumRepository.findTop6ByOrderByIdAsc();
        return albums.stream().map(this::convertToDTOWithArtist).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsForArtist(int artistId) {
        List<Album> albums = albumRepository.findByAlbumArtists_Artist_Id(artistId);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        return albumDTO;
    }

    public AlbumDTO convertToDTOWithArtist(Album album) {
        List<Artist> artists = artistRepository.findByAlbumArtists_Album_Id(album.getId());
        List<ArtistDTO> artistDTOS = new ArrayList<>();
        for(Artist artist : artists) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(artist.getId());
            artistDTO.setArtistName(artist.getArtistName());
            artistDTOS.add(artistDTO);
        }
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        albumDTO.setAlbumArtists(artistDTOS);
        return albumDTO;
    }
}
