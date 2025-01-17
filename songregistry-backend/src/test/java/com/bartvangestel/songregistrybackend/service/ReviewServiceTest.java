package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IReviewDAL;
import com.bartvangestel.songregistrybackend.logic.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private IReviewDAL reviewDAL;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addReviewSuccessfully() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewService.addReview(reviewDTO);
        verify(reviewDAL, times(1)).addReview(reviewDTO);
    }

    @Test
    void getReviewsForSongSuccessfully() {
        int songId = 1;
        List<ReviewDTO> expectedReviews = List.of(new ReviewDTO());
        when(reviewDAL.getReviewsForSong(songId)).thenReturn(expectedReviews);

        List<ReviewDTO> actualReviews = reviewService.getReviewsForSong(songId);
        assertEquals(expectedReviews, actualReviews);
    }

    @Test
    void getReviewsForSongWithNoReviews() {
        int songId = 1;
        when(reviewDAL.getReviewsForSong(songId)).thenReturn(Collections.emptyList());

        List<ReviewDTO> actualReviews = reviewService.getReviewsForSong(songId);
        assertEquals(Collections.emptyList(), actualReviews);
    }
}