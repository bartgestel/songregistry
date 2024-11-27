package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.ReviewDTO;

import java.util.List;

public interface IReviewDAL {
    void addReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getReviewsForSong(int id);
}
