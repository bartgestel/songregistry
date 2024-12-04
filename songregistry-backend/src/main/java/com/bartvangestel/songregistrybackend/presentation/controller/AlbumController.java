package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.logic.service.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping()
    public List<AlbumDTO> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping("/home")
    public List<AlbumDTO> getAlbumsForHome() {
        return albumService.getAlbumsForHome();
    }

    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable int id) {
        return albumService.getAlbumById(id);
    }
}
