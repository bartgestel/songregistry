package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.logic.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.logic.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Operation(summary = "Get all artists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artists returned"),
    })
    @GetMapping
    public List<ArtistDTO> getArtists() {
        return artistService.getArtists();
    }

    @Operation(summary = "Get artists for home page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artists returned"),
    })
    @GetMapping("/home")
    public List<ArtistDTO> getArtistsForHome() {
        return artistService.getArtistsForHome();
    }

    @Operation(summary = "Get artist by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist returned"),
            @ApiResponse(responseCode = "404", description = "Artist not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable int id) {
        ArtistDTO artist = artistService.getArtistById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(artist);
        }
    }

    @Operation(summary = "Add artist to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artist added"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping()
    public ResponseEntity<Void> addArtist(@RequestBody ArtistDTO artistDTO) {
        artistService.addArtist(artistDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
