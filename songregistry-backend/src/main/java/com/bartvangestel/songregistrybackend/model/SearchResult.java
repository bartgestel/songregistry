package com.bartvangestel.songregistrybackend.model;

import java.util.List;

public class SearchResult {
    private List<Artist> artists;
    private List<Album> albums;
    private List<Song> songs;

    public SearchResult(List<Artist> artists, List<Album> albums, List<Song> songs) {
        this.artists = artists;
        this.albums = albums;
        this.songs = songs;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
