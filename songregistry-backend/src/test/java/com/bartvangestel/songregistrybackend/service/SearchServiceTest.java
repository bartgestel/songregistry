// src/test/java/com/bartvangestel/songregistrybackend/service/SearchServiceImplTest.java
package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.service.interfaces.ISearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private ArtistServiceImpl artistServiceImpl;

    @Mock
    private AlbumServiceImpl albumServiceImpl;

    @Mock
    private SongServiceImpl songServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchWithArtists() {
        // Arrange
        String search = "ArtistName";
        Artist artist = new Artist(1, search); // Assuming the constructor matches your model
        List<Artist> artists = new ArrayList<>();
        artists.add(artist);

        when(artistServiceImpl.getArtistByName(search)).thenReturn(artists);
        when(albumServiceImpl.getAlbumsByAlbumTitle(search)).thenReturn(new ArrayList<>());
        when(songServiceImpl.getSongsBySongTitle(search)).thenReturn(new ArrayList<>());

        // Act
        List<SearchResult> results = searchService.search(search);

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
        Album album = new Album(1, search); // Assuming the constructor matches your model
        List<Album> albums = new ArrayList<>();
        albums.add(album);

        when(artistServiceImpl.getArtistByName(search)).thenReturn(new ArrayList<>());
        when(albumServiceImpl.getAlbumsByAlbumTitle(search)).thenReturn(albums);
        when(songServiceImpl.getSongsBySongTitle(search)).thenReturn(new ArrayList<>());

        // Act
        List<SearchResult> results = searchService.search(search);

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
        Song song = new Song(1, search); // Assuming the constructor matches your model
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        when(artistServiceImpl.getArtistByName(search)).thenReturn(new ArrayList<>());
        when(albumServiceImpl.getAlbumsByAlbumTitle(search)).thenReturn(new ArrayList<>());
        when(songServiceImpl.getSongsBySongTitle(search)).thenReturn(songs);

        // Act
        List<SearchResult> results = searchService.search(search);

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
        Artist artist = new Artist(1, "ArtistName");
        Album album = new Album(2, "AlbumName");
        Song song = new Song(3, "SongName");

        List<Artist> artists = new ArrayList<>();
        artists.add(artist);

        List<Album> albums = new ArrayList<>();
        albums.add(album);

        List<Song> songs = new ArrayList<>();
        songs.add(song);

        when(artistServiceImpl.getArtistByName(search)).thenReturn(artists);
        when(albumServiceImpl.getAlbumsByAlbumTitle(search)).thenReturn(albums);
        when(songServiceImpl.getSongsBySongTitle(search)).thenReturn(songs);

        // Act
        List<SearchResult> results = searchService.search(search);

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
