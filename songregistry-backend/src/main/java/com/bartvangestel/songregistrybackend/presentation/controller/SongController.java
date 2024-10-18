package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.logic.service.SongService;
import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public List<Song> getSongs() {
        return songService.getSongs();
    }
}