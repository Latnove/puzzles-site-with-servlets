package dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dao.CategoryDao;
import entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private HikariDataSource dataSource;

    public CategoryDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setId(resultSet.getInt("id"));
                    category.setValue(resultSet.getString("name"));
                    category.setNameRu(resultSet.getString("name_ru"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public List<Category> getAllCategoriesByNames(String[] values) {
        if (values == null || values.length == 0) {
            return Collections.emptyList();
        }

        List<Category> categoryList = new ArrayList<>();

        String placeholders = String.join(",", Collections.nCopies(values.length, "?"));
        String sql = "SELECT id, name, name_ru FROM categories WHERE name IN (" + placeholders + ");";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                ps.setString(i + 1, values[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setValue(rs.getString("name"));
                    category.setNameRu(rs.getString("name_ru"));
                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get categories by values", e);
        }

        return categoryList;
    }

    @Override
    public List<Category> getCategoriesByPuzzleId(Integer puzzleId) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.name_ru FROM categories c JOIN puzzle_categories pc ON c.id = pc.category_id WHERE pc.puzzle_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, puzzleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setValue(rs.getString("name"));
                    category.setNameRu(rs.getString("name_ru"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
