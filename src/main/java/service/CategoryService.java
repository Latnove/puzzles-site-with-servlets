package service;

import com.zaxxer.hikari.HikariDataSource;
import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import entity.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(HikariDataSource dataSource) {
        this.categoryDao = new CategoryDaoImpl(dataSource);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public List<Category> getAllCategoriesByNames(String[] values) {
        return categoryDao.getAllCategoriesByNames(values);
    }

    public List<Category> getCategoriesByPuzzleId(int id) {
        return categoryDao.getCategoriesByPuzzleId(id);
    }
}