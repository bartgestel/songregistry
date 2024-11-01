package com.bartvangestel.songregistrybackend.dal.model;

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

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    private Set<AlbumArtist> albumArtists = new LinkedHashSet<>();

    public Album() {
    }

    public Album(Integer id, String albumName) {
        this.id = id;
        this.albumName = albumName;
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

    public Set<AlbumArtist> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(Set<AlbumArtist> albumArtists) {
        this.albumArtists = albumArtists;
    }

}