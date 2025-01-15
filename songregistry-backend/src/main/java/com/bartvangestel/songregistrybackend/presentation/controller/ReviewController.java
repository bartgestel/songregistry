package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.logic.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.logic.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Add a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review added"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(reviewDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get reviews for a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews returned"),
            @ApiResponse(responseCode = "404", description = "Song not found"),
    })
    @GetMapping("/song/{id}")
    public ResponseEntity<List<ReviewDTO>> getReviewsForSong(@PathVariable int id) {
        List<ReviewDTO> review = reviewService.getReviewsForSong(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(review);
        }
    }
}
