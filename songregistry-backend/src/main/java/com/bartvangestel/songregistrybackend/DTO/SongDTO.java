package com.bartvangestel.songregistrybackend.DTO;

import java.util.List;

public class SongDTO {
    private int id;
    private String title;
    private List<ArtistDTO> artists;

    public SongDTO() {
    }

    public SongDTO(int id, String title, List<ArtistDTO> artists) {
        this.id = id;
        this.title = title;
        this.artists = artists;
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

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}
