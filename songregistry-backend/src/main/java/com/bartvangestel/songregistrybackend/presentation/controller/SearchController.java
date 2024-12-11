package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SearchResultDTO;
import com.bartvangestel.songregistrybackend.logic.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{search}")
    public List<SearchResultDTO> search(@PathVariable String search) {
        return searchService.search(search);
    }

    @GetMapping("/{search}/artist")
    public List<ArtistDTO> searchArtist(@PathVariable String search) {
        return searchService.searchArtist(search);
    }

    @GetMapping("/{search}/album")
    public List<AlbumDTO> searchAlbum(@PathVariable String search) {
        return searchService.searchAlbum(search);
    }
}
