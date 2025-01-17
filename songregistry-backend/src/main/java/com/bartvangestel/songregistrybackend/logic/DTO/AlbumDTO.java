package com.bartvangestel.songregistrybackend.logic.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumDTO {
    private Integer id;
    private String albumName;
    private List<ArtistDTO> albumArtists;
    private List<SongDTO> albumSongs;
    private double averageRating;

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

    public List<SongDTO> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(List<SongDTO> albumSongs) {
        this.albumSongs = albumSongs;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
