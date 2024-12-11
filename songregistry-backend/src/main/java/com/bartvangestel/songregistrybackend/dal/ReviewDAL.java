package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.dal.model.Review;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import com.bartvangestel.songregistrybackend.dal.repository.ReviewRepository;
import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IReviewDAL;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAL implements IReviewDAL {

    private final ReviewRepository reviewRepository;
    private final SongRepository songRepository;

    public ReviewDAL(ReviewRepository reviewRepository, SongRepository songRepository) {
        this.reviewRepository = reviewRepository;
        this.songRepository = songRepository;
    }

    public void addReview(ReviewDTO reviewDTO) {
        Song song = songRepository.findById(reviewDTO.getSongId()).get(0);
        Review review = new Review();
        review.setSong(song);
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getReview());
        reviewRepository.save(review);
    }

    public List<ReviewDTO> getReviewsForSong(int id) {
        List<Review> reviews = reviewRepository.findReviewBySongId(id, Sort.by(Sort.Direction.DESC, "id"));
        if (reviews.isEmpty()) {
            return null;
        }
        return reviews.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());

    }

    public ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setRating(review.getRating());
        reviewDTO.setReview(review.getText());
        reviewDTO.setSongId(review.getSong().getId());
        return reviewDTO;
    }
}
