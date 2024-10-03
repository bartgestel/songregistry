package com.bartvangestel.songregistrybackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    private int id;
    @Column(name = "artist_name")
    private String artistName;

    public Artist(){

    }

    public Artist(int id, String artist_name) {
        this.id = id;
        this.artistName = artist_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist_name() {
        return artistName;
    }

    public void setArtist_name(String name) {
        this.artistName = name;
    }
}
