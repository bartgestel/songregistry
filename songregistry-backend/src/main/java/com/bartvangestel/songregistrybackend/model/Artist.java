package com.bartvangestel.songregistrybackend.model;

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

    @OneToMany(mappedBy = "artist")
    private Set<SongArtist> songArtists = new LinkedHashSet<>();

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

    public Set<SongArtist> getSongArtists() {
        return songArtists;
    }

    public void setSongArtists(Set<SongArtist> songArtists) {
        this.songArtists = songArtists;
    }

}