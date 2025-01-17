package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.logic.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SongServiceTest {

    @Mock
    private ISongDAL songDAL;

    @InjectMocks
    private SongService songService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSongs_returnsListOfSongs() {
        List<SongDTO> expectedSongs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songDAL.getSongs()).thenReturn(expectedSongs);

        List<SongDTO> actualSongs = songService.getSongs();

        assertEquals(expectedSongs, actualSongs);
    }

    @Test
    void getSongsByArtistName_returnsSongsByArtist() {
        String artistName = "Artist";
        List<SongDTO> expectedSongs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songDAL.getSongsByArtistName(artistName)).thenReturn(expectedSongs);

        List<SongDTO> actualSongs = songService.getSongsByArtistName(artistName);

        assertEquals(expectedSongs, actualSongs);
    }

    @Test
    void getSongsBySongTitle_returnsSongsByTitle() {
        String songTitle = "Title";
        List<SongDTO> expectedSongs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songDAL.getSongsBySongTitle(songTitle)).thenReturn(expectedSongs);

        List<SongDTO> actualSongs = songService.getSongsBySongTitle(songTitle);

        assertEquals(expectedSongs, actualSongs);
    }

    @Test
    void getSongById_returnsSongById() {
        int songId = 1;
        SongDTO expectedSong = new SongDTO();
        when(songDAL.getSongById(songId)).thenReturn(expectedSong);

        SongDTO actualSong = songService.getSongById(songId);

        assertEquals(expectedSong, actualSong);
    }

    @Test
    void addSong_addsSong() {
        SongDTO newSong = new SongDTO();

        songService.addSong(newSong);

        verify(songDAL, times(1)).addSong(newSong);
    }

    @Test
    void getSongs_returnsEmptyListWhenNoSongs() {
        when(songDAL.getSongs()).thenReturn(Collections.emptyList());

        List<SongDTO> actualSongs = songService.getSongs();

        assertEquals(Collections.emptyList(), actualSongs);
    }

    @Test
    void getSongsByArtistName_returnsEmptyListWhenNoSongs() {
        String artistName = "NonExistentArtist";
        when(songDAL.getSongsByArtistName(artistName)).thenReturn(Collections.emptyList());

        List<SongDTO> actualSongs = songService.getSongsByArtistName(artistName);

        assertEquals(Collections.emptyList(), actualSongs);
    }

    @Test
    void getSongsBySongTitle_returnsEmptyListWhenNoSongs() {
        String songTitle = "NonExistentTitle";
        when(songDAL.getSongsBySongTitle(songTitle)).thenReturn(Collections.emptyList());

        List<SongDTO> actualSongs = songService.getSongsBySongTitle(songTitle);

        assertEquals(Collections.emptyList(), actualSongs);
    }

    @Test
    void getSongById_returnsNullWhenSongNotFound() {
        int songId = 999;
        when(songDAL.getSongById(songId)).thenReturn(null);

        SongDTO actualSong = songService.getSongById(songId);

        assertEquals(null, actualSong);
    }
}