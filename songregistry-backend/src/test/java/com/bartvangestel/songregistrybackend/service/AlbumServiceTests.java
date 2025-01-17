package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.IReviewDAL;
import com.bartvangestel.songregistrybackend.logic.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumServiceTests {

    @Mock
    private IAlbumDAL albumDAL;

    @Mock
    private IReviewDAL reviewDAL;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAlbums_returnsListOfAlbums() {
        List<AlbumDTO> albums = List.of(new AlbumDTO());
        when(albumDAL.getAlbums()).thenReturn(albums);

        List<AlbumDTO> result = albumService.getAlbums();

        assertEquals(albums, result);
    }

    @Test
    void getAlbumsByArtistName_returnsAlbumsByArtist() {
        String artistName = "Artist";
        List<AlbumDTO> albums = List.of(new AlbumDTO());
        when(albumDAL.getAlbumsByArtistName(artistName)).thenReturn(albums);

        List<AlbumDTO> result = albumService.getAlbumsByArtistName(artistName);

        assertEquals(albums, result);
    }

    @Test
    void getAlbumsByAlbumTitle_returnsAlbumsByTitle() {
        String title = "Title";
        List<AlbumDTO> albums = List.of(new AlbumDTO());
        when(albumDAL.getAlbumsByAlbumTitle(title)).thenReturn(albums);

        List<AlbumDTO> result = albumService.getAlbumsByAlbumTitle(title);

        assertEquals(albums, result);
    }

    @Test
    void getAlbumsForHome_returnsAlbumsForHome() {
        List<AlbumDTO> albums = List.of(new AlbumDTO());
        when(albumDAL.getAlbumsForHome()).thenReturn(albums);

        List<AlbumDTO> result = albumService.getAlbumsForHome();

        assertEquals(albums, result);
    }

    @Test
    void getAlbumById_returnsAlbumWithAverageRating() {
        int albumId = 1;
        AlbumDTO album = new AlbumDTO();
        SongDTO song = new SongDTO();
        song.setId(1);
        album.setAlbumSongs(List.of(song));
        ReviewDTO review = new ReviewDTO();
        review.setRating(4.0);
        when(albumDAL.getAlbumById(albumId)).thenReturn(album);
        when(reviewDAL.getReviewsForSong(song.getId())).thenReturn(List.of(review));

        AlbumDTO result = albumService.getAlbumById(albumId);

        assertEquals(4.0, result.getAverageRating());
    }

    @Test
    void getAlbumById_returnsAlbumWithZeroAverageRatingWhenNoReviews() {
        int albumId = 1;
        AlbumDTO album = new AlbumDTO();
        SongDTO song = new SongDTO();
        song.setId(1);
        album.setAlbumSongs(List.of(song));
        when(albumDAL.getAlbumById(albumId)).thenReturn(album);
        when(reviewDAL.getReviewsForSong(song.getId())).thenReturn(Collections.emptyList());

        AlbumDTO result = albumService.getAlbumById(albumId);

        assertEquals(0.0, result.getAverageRating());
    }

    @Test
    void addAlbum_addsAlbum() {
        AlbumDTO albumDTO = new AlbumDTO();

        albumService.addAlbum(albumDTO);

        // Verify that the albumDAL.addAlbum method was called with the correct argument
        verify(albumDAL).addAlbum(albumDTO);
    }
}