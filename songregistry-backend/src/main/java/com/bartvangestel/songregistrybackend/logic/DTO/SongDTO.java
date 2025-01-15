package com.bartvangestel.songregistrybackend.logic.DTO;

import java.util.List;

public class SongDTO {
    private int id;
    private String title;
    private int albumId;
    private List<ArtistDTO> artists;
    private List<ReviewDTO> reviews;

    public SongDTO() {
    }

    public SongDTO(int id, String title, List<ArtistDTO> artists, List<ReviewDTO> reviews) {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
