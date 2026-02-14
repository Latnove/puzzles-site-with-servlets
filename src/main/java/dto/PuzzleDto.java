package dto;

import entity.Category;

import java.util.List;

public class PuzzleDto {
    private Integer id;
    private String title;
    private String imageUrl;
    private Integer pieceCols;
    private Integer pieceRows;
    private String status;
    private Integer userId;
    private Integer moderatorId;
    private double rating;
    private String createdAt;
    private List<Category> categories;
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public PuzzleDto() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getPieceCols() { return pieceCols; }

    public void setPieceCols(Integer pieceCols) { this.pieceCols = pieceCols; }

    public Integer getPieceRows() { return pieceRows; }

    public void setPieceRows(Integer pieceRows) { this.pieceRows = pieceRows; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getModeratorId() { return moderatorId; }

    public void setModeratorId(Integer moderatorId) { this.moderatorId = moderatorId; }

    public String getCreatedAt() { return createdAt; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

}
