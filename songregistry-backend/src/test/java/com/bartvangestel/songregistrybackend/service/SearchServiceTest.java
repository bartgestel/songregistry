// src/test/java/com/bartvangestel/songregistrybackend/service/SearchServiceTest.java
package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistService;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.logic.service.SearchService;
import com.bartvangestel.songregistrybackend.logic.DTO.SearchResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private IArtistService artistService;

    @Mock
    private IAlbumService albumService;

    @Mock
    private ISongService songService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchWithArtists() {
        // Arrange
        String search = "ArtistName";
        ArtistDTO artist = new ArtistDTO();
        artist.setId(1);
        artist.setArtistName(search);
        List<ArtistDTO> artists = new ArrayList<>();
        artists.add(artist);

        when(artistService.getArtistByName(search)).thenReturn(artists);
        when(albumService.getAlbumsByAlbumTitle(search)).thenReturn(new ArrayList<>());
        when(songService.getSongsBySongTitle(search)).thenReturn(new ArrayList<>());

        // Act
        List<SearchResultDTO> results = searchService.search(search);

        // Assert
        assertEquals(1, results.size());
        assertEquals("artist", results.get(0).getType());
        assertEquals(1, results.get(0).getId());
        assertEquals(search, results.get(0).getName());
    }

    @Test
    void testSearchWithAlbums() {
        // Arrange
        String search = "AlbumName";
        AlbumDTO album = new AlbumDTO();
        album.setId(1);
        album.setAlbumName(search);
        List<AlbumDTO> albums = new ArrayList<>();
        albums.add(album);

        when(artistService.getArtistByName(search)).thenReturn(new ArrayList<>());
        when(albumService.getAlbumsByAlbumTitle(search)).thenReturn(albums);
        when(songService.getSongsBySongTitle(search)).thenReturn(new ArrayList<>());

        // Act
        List<SearchResultDTO> results = searchService.search(search);

        // Assert
        assertEquals(1, results.size());
        assertEquals("album", results.get(0).getType());
        assertEquals(1, results.get(0).getId());
        assertEquals(search, results.get(0).getName());
    }

    @Test
    void testSearchWithSongs() {
        // Arrange
        String search = "SongName";
        SongDTO song = new SongDTO();
        song.setId(1);
        song.setTitle(search);
        List<SongDTO> songs = new ArrayList<>();
        songs.add(song);

        when(artistService.getArtistByName(search)).thenReturn(new ArrayList<>());
        when(albumService.getAlbumsByAlbumTitle(search)).thenReturn(new ArrayList<>());
        when(songService.getSongsBySongTitle(search)).thenReturn(songs);

        // Act
        List<SearchResultDTO> results = searchService.search(search);

        // Assert
        assertEquals(1, results.size());
        assertEquals("song", results.get(0).getType());
        assertEquals(1, results.get(0).getId());
        assertEquals(search, results.get(0).getName());
    }

    @Test
    void testSearchWithMultipleResults() {
        // Arrange
        String search = "SearchTerm";
        ArtistDTO artist = new ArtistDTO();
        artist.setId(1);
        artist.setArtistName("ArtistName");
        AlbumDTO album = new AlbumDTO();
        album.setId(2);
        album.setAlbumName("AlbumName");
        SongDTO song = new SongDTO();
        song.setId(3);
        song.setTitle("SongName");

        List<ArtistDTO> artists = new ArrayList<>();
        artists.add(artist);

        List<AlbumDTO> albums = new ArrayList<>();
        albums.add(album);

        List<SongDTO> songs = new ArrayList<>();
        songs.add(song);

        when(artistService.getArtistByName(search)).thenReturn(artists);
        when(albumService.getAlbumsByAlbumTitle(search)).thenReturn(albums);
        when(songService.getSongsBySongTitle(search)).thenReturn(songs);

        // Act
        List<SearchResultDTO> results = searchService.search(search);

        // Assert
        assertEquals(3, results.size());
        assertEquals("artist", results.get(0).getType());
        assertEquals(1, results.get(0).getId());
        assertEquals("ArtistName", results.get(0).getName());

        assertEquals("album", results.get(1).getType());
        assertEquals(2, results.get(1).getId());
        assertEquals("AlbumName", results.get(1).getName());

        assertEquals("song", results.get(2).getType());
        assertEquals(3, results.get(2).getId());
        assertEquals("SongName", results.get(2).getName());
    }
}