package dto;

public class CreatedPuzzleDto {
    private Integer id;
    private String title;
    private String imageUrl;
    private int pieceCols;
    private int pieceRows;
    private String status;
    private long createdAt;

    public CreatedPuzzleDto(Integer id, String title, String imageUrl, int pieceCols, int pieceRows, String status, long createdAt) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.pieceCols = pieceCols;
        this.pieceRows = pieceRows;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public long getCreatedAt() {
        return createdAt;
    }


}
