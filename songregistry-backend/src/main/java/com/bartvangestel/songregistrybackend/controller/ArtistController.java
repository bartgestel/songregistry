package com.bartvangestel.songregistrybackend.controller;


import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.service.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistServiceImpl artistServiceImpl;

    @GetMapping
    public List<Artist> getArtists() {
        return artistServiceImpl.getArtists();
    }

    @GetMapping("/{name}")
    public List<Artist> getArtistByName(@PathVariable String name) {
        return artistServiceImpl.getArtistByName(name);
    }

    @GetMapping("/{name}/albums")
    public List<Album> getAlbumsByArtistName(@PathVariable String name) {
        return artistServiceImpl.getAlbumsByArtistName(name);
    }

    @GetMapping("/{name}/songs")
    public List<Song> getSongsByArtistName(@PathVariable String name) {
        return artistServiceImpl.getSongsByArtistName(name);
    }


}
