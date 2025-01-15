package com.bartvangestel.songregistrybackend.logic.DTO;

import java.util.List;

public class ArtistDTO {
    private Integer id;
    private String artistName;
    private List<AlbumDTO> artistAlbums;
    private List<SongDTO> artistSongs;

    public ArtistDTO() {
    }

    public ArtistDTO(Integer id, String artistName, List<AlbumDTO> artistAlbums, List<SongDTO> artistSongs) {
        this.id = id;
        this.artistName = artistName;
        this.artistAlbums = artistAlbums;
        this.artistSongs = artistSongs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<AlbumDTO> getArtistAlbums() {
        return artistAlbums;
    }

    public void setArtistAlbums(List<AlbumDTO> artistAlbums) {
        this.artistAlbums = artistAlbums;
    }

    public List<SongDTO> getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(List<SongDTO> artistSongs) {
        this.artistSongs = artistSongs;
    }
}
