package com.bartvangestel.songregistrybackend.DTO;

import java.util.List;

public class AlbumDTO {
    private Integer id;
    private String albumName;
    private List<ArtistDTO> albumArtists;

    public AlbumDTO() {
    }

    public AlbumDTO(Integer id, String albumName, List<ArtistDTO> albumArtists) {
        this.id = id;
        this.albumName = albumName;
        this.albumArtists = albumArtists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<ArtistDTO> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(List<ArtistDTO> albumArtists) {
        this.albumArtists = albumArtists;
    }
}
