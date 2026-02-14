package dto;

import entity.Review;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReviewDto {
    private Integer id;
    private Integer puzzleId;
    private Integer userId;
    private Integer rating;
    private String reviewText;
    private UserDto user;
    private String createdAt;

    public ReviewDto() {}

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.puzzleId = review.getPuzzleId();
        this.userId = review.getUserId();
        this.rating = review.getRating();
        this.reviewText = review.getReviewText();
        this.user = review.getUser();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        this.createdAt = sdf.format(review.getCreatedAt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Integer puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
