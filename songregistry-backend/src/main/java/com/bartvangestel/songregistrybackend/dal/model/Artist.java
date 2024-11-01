package com.bartvangestel.songregistrybackend.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<AlbumArtist> albumArtists = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    private Set<SongArtist> songArtists = new LinkedHashSet<>();

    public Artist() {
    }

    public Artist(Integer id, String artistName) {
        this.id = id;
        this.artistName = artistName;
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

    public Set<AlbumArtist> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(Set<AlbumArtist> albumArtists) {
        this.albumArtists = albumArtists;
    }

    public Set<SongArtist> getSongArtists() {
        return songArtists;
    }

    public void setSongArtists(Set<SongArtist> songArtists) {
        this.songArtists = songArtists;
    }

}