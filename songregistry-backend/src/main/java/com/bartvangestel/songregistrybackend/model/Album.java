package com.bartvangestel.songregistrybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    private Set<AlbumArtist> albumArtists = new LinkedHashSet<>();

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

    public Set<AlbumArtist> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(Set<AlbumArtist> albumArtists) {
        this.albumArtists = albumArtists;
    }

}