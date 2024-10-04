package com.bartvangestel.songregistrybackend.model;

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
    private Genre songGenre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @OneToMany(mappedBy = "song")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "song")
    private Set<SongArtist> songArtists = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
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