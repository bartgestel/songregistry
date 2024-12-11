package com.bartvangestel.songregistrybackend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReviewDTO {
    @JsonIgnore
    private int id;
    private int songId;
    private String review;
    private Double rating;

    public ReviewDTO() {
    }

    public ReviewDTO(int id, int songId, String text, Double rating) {
        this.id = id;
        this.songId = songId;
        this.review = text;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
