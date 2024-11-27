package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IReviewDAL;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final IReviewDAL reviewDAL;

    public ReviewService(IReviewDAL reviewDAL) {
        this.reviewDAL = reviewDAL;
    }

    public void addReview(ReviewDTO reviewDTO) {
        reviewDAL.addReview(reviewDTO);
    }

    public List<ReviewDTO> getReviewsForSong(int id) {
        return reviewDAL.getReviewsForSong(id);
    }
}
