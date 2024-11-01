package com.bartvangestel.songregistrybackend.presentation.controller;


import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import com.bartvangestel.songregistrybackend.logic.service.ArtistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<ArtistDTO> getArtists() {
        return artistService.getArtists();
    }

    @GetMapping("/home")
    public List<ArtistDTO> getArtistsForHome() {
        return artistService.getArtistsForHome();
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable int id) {
        return artistService.getArtistById(id);
    }

    @GetMapping("/{id}/albums")
    public List<AlbumDTO> getAlbumsByArtistName(@PathVariable String name) {
        return artistService.getAlbumsByArtistName(name);
    }

    @GetMapping("/{id}/songs")
    public List<SongDTO> getSongsByArtistName(@PathVariable String name) {
        return artistService.getSongsByArtistName(name);
    }


}
