package dao;

import entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByNames(String[] values);
    List<Category> getCategoriesByPuzzleId(Integer puzzleId);
}
