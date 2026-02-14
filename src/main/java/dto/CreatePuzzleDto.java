package dto;

import entity.Category;

import java.util.List;

public class CreatePuzzleDto {
    private String title;
    private int pieceCols;
    private int pieceRows;
    private Integer userId;
    private List<Category> categories;

    public CreatePuzzleDto(String title, int pieceCols, int pieceRows, Integer userId, List<Category> categories) {
        this.title = title;
        this.pieceCols = pieceCols;
        this.pieceRows = pieceRows;
        this.userId = userId;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
