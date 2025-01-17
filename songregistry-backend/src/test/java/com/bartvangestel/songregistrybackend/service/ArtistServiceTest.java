package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.logic.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    @Mock
    private IArtistDAL artistDAL;

    @Mock
    private IAlbumService albumService;

    @Mock
    private ISongService songService;

    @InjectMocks
    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getArtists_returnsListOfArtists() {
        List<ArtistDTO> artists = Arrays.asList(new ArtistDTO(), new ArtistDTO());
        when(artistDAL.getArtists()).thenReturn(artists);

        List<ArtistDTO> result = artistService.getArtists();

        assertEquals(artists, result);
    }

    @Test
    void getArtistById_returnsArtist() {
        ArtistDTO artist = new ArtistDTO();
        when(artistDAL.getArtistById(1)).thenReturn(artist);

        ArtistDTO result = artistService.getArtistById(1);

        assertEquals(artist, result);
    }

    @Test
    void getArtistByName_returnsListOfArtists() {
        List<ArtistDTO> artists = Arrays.asList(new ArtistDTO(), new ArtistDTO());
        when(artistDAL.getArtistsByName("name")).thenReturn(artists);

        List<ArtistDTO> result = artistService.getArtistByName("name");

        assertEquals(artists, result);
    }

    @Test
    void getArtistsForHome_returnsListOfArtists() {
        List<ArtistDTO> artists = Arrays.asList(new ArtistDTO(), new ArtistDTO());
        when(artistDAL.getArtistsForHome()).thenReturn(artists);

        List<ArtistDTO> result = artistService.getArtistsForHome();

        assertEquals(artists, result);
    }

    @Test
    void getAlbumsByArtistName_returnsListOfAlbums() {
        List<AlbumDTO> albums = Arrays.asList(new AlbumDTO(), new AlbumDTO());
        when(albumService.getAlbumsByArtistName("name")).thenReturn(albums);

        List<AlbumDTO> result = artistService.getAlbumsByArtistName("name");

        assertEquals(albums, result);
    }

    @Test
    void getSongsByArtistName_returnsListOfSongs() {
        List<SongDTO> songs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songService.getSongsByArtistName("name")).thenReturn(songs);

        List<SongDTO> result = artistService.getSongsByArtistName("name");

        assertEquals(songs, result);
    }

    @Test
    void addArtist_addsArtist() {
        ArtistDTO artist = new ArtistDTO();
        artistService.addArtist(artist);

        when(artistDAL.getArtists()).thenReturn(Collections.singletonList(artist));
        List<ArtistDTO> result = artistService.getArtists();

        assertEquals(1, result.size());
        assertEquals(artist, result.get(0));
    }
}