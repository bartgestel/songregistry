package com.bartvangestel.songregistrybackend.controller;


import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Artist> getArtistById(@PathVariable int id) {
        return artistRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Artist> getArtistByName(@PathVariable String name) {
        return artistRepository.findByArtistNameContainingIgnoreCase(name);
    }


}
