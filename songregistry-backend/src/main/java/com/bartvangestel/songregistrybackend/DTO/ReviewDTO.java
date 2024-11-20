package com.bartvangestel.songregistrybackend.DTO;

public class ReviewDTO {
    private int id;
    private int songId;
    private String text;
    private Double rating;

    public ReviewDTO() {
    }

    public ReviewDTO(int id, int songId, String review, Double rating) {
        this.id = id;
        this.songId = songId;
        this.text = review;
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
        return text;
    }

    public void setReview(String review) {
        this.text = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
