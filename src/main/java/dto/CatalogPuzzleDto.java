package dto;

public class CatalogPuzzleDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String imageUrl;
    private int pieceCols;
    private int pieceRows;
    private double rating;
    private long createdAt;
    private int completionsCount;

    public CatalogPuzzleDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCompletionsCount(int completionsCount) {
        this.completionsCount = completionsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPieceCols() {
        return pieceCols;
    }

    public void setPieceCols(int pieceCols) {
        this.pieceCols = pieceCols;
    }

    public int getPieceRows() {
        return pieceRows;
    }

    public void setPieceRows(int pieceRows) {
        this.pieceRows = pieceRows;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getCompletionsCount() {
        return completionsCount;
    }
}
