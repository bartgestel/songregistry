package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SearchServiceTest {

    @Mock
    private ArtistService artistService;

    @Mock
    private AlbumService albumService;

    @Mock
    private SongService songService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearch() {
        String searchQuery = "test";

        Artist artist = new Artist();
        artist.setId(1);
        artist.setArtistName("Test Artist");

        Album album = new Album();
        album.setId(2);
        album.setAlbumName("Test Album");

        Song song = new Song();
        song.setId(3);
        song.setSongName("Test Song");

        when(artistService.getArtistByName(searchQuery)).thenReturn(Arrays.asList(artist));
        when(albumService.getAlbumsByAlbumTitle(searchQuery)).thenReturn(Arrays.asList(album));
        when(songService.getSongsBySongTitle(searchQuery)).thenReturn(Arrays.asList(song));

        List<SearchResult> results = searchService.search(searchQuery);

        assertEquals(3, results.size());

        assertEquals("artist", results.get(0).getType());
        assertEquals(1, results.get(0).getId());
        assertEquals("Test Artist", results.get(0).getName());

        assertEquals("album", results.get(1).getType());
        assertEquals(2, results.get(1).getId());
        assertEquals("Test Album", results.get(1).getName());

        assertEquals("song", results.get(2).getType());
        assertEquals(3, results.get(2).getId());
        assertEquals("Test Song", results.get(2).getName());
    }
}