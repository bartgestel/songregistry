// src/main/java/com/bartvangestel/songregistrybackend/model/Song.java
package com.bartvangestel.songregistrybackend.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "song_name", nullable = false)
    private String songName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_genre", nullable = false)
    @JsonIgnore
    private Genre songGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    @JsonIgnore
    private Album album;

    @JsonIgnore
    @OneToMany(mappedBy = "song")
    private Set<Review> reviews = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "song")
    private Set<SongArtist> songArtists = new LinkedHashSet<>();

    public Song() {
    }

    public Song(Integer id, String songName) {
        this.id = id;
        this.songName = songName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return songName;
    }

    public void setTitle(String songName) {
        this.songName = songName;
    }

    public Genre getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(Genre songGenre) {
        this.songGenre = songGenre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<SongArtist> getSongArtists() {
        return songArtists;
    }

    public void setSongArtists(Set<SongArtist> songArtists) {
        this.songArtists = songArtists;
    }
}