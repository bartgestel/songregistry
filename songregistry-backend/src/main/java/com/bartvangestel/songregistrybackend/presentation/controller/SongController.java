package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.service.SongService;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public List<SongDTO> getSongs() {
        return songService.getSongs();
    }

    @PostMapping
    public void addSong(@RequestBody SongDTO songDTO) {
        System.out.println(songDTO.getAlbumId());
        songService.addSong(songDTO);
    }

    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable int id) {
        return songService.getSongById(id);
    }
}
