package dto;

public class CollectPuzzleDto {

    private Integer id;
    private String title;
    private String imageUrl;
    private int pieceCols;
    private int pieceRows;
    private double rating;
    private long createdAt;

    public CollectPuzzleDto() {
    }

    public CollectPuzzleDto(Integer id, String title, String imageUrl, int pieceCols, int pieceRows, double rating, long createdAt) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.pieceCols = pieceCols;
        this.pieceRows = pieceRows;
        this.rating = rating;
        this.createdAt = createdAt;
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

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}