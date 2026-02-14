package dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dao.ReviewDao;
import dto.UserDto;
import entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private HikariDataSource dataSource;

    public ReviewDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Review> getAllReviewsByPuzzleId(Integer puzzleId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.id AS review_id, r.puzzle_id, r.user_id, r.rating, r.review_text, r.created_at AS review_created_at, u.id AS user_id, u.username, u.email, u.image, u.role, u.created_at AS user_created_at FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.puzzle_id = ? ORDER BY r.created_at DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, puzzleId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserDto user = new UserDto(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),  rs.getString("image"), rs.getString("role"), rs.getTimestamp("user_created_at"));

                    Review review = new Review();
                    review.setId(rs.getInt("review_id"));
                    review.setPuzzleId(rs.getInt("puzzle_id"));
                    review.setUserId(rs.getInt("user_id"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewText(rs.getString("review_text"));
                    review.setCreatedAt(rs.getTimestamp("review_created_at"));
                    review.setUser(user);
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review getPuzzleReviewByUserId(Integer puzzleId, Integer userId) {
        Review review = null;
        String sql = "SELECT r.id AS review_id, r.puzzle_id, r.user_id, r.rating, r.review_text, r.created_at AS review_created_at, u.id AS user_id, u.username, u.email, u.image, u.role, u.created_at AS user_created_at FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.puzzle_id = ? AND r.user_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, puzzleId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    review = new Review();

                    UserDto user = new UserDto(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),  rs.getString("image"), rs.getString("role"), rs.getTimestamp("user_created_at"));

                    review.setId(rs.getInt("review_id"));
                    review.setPuzzleId(rs.getInt("puzzle_id"));
                    review.setUserId(rs.getInt("user_id"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewText(rs.getString("review_text"));
                    review.setCreatedAt(rs.getTimestamp("review_created_at"));
                    review.setUser(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public List<Review> getReviewsByPuzzleIdIgnoredUserId(Integer puzzleId, Integer userId, Integer offset, Integer limit) {
        List<Review> reviews = new ArrayList<>();
        String sql;

        if (userId != null) {
            sql = "SELECT r.id AS review_id, r.puzzle_id, r.user_id, r.rating, r.review_text, r.created_at AS review_created_at, u.id AS user_id, u.username, u.email, u.image, u.role, u.created_at AS user_created_at FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.puzzle_id = ? AND r.user_id != ? ORDER BY r.created_at DESC LIMIT ? OFFSET ? ";
        } else {
            sql = "SELECT r.id AS review_id, r.puzzle_id, r.user_id, r.rating, r.review_text, r.created_at AS review_created_at, u.id AS user_id, u.username, u.email, u.image, u.role, u.created_at AS user_created_at FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.puzzle_id = ? ORDER BY r.created_at DESC LIMIT ? OFFSET ?";
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (userId != null) {
                ps.setInt(1, puzzleId);
                ps.setInt(2, userId);
                ps.setInt(3, limit);
                ps.setInt(4, offset);
            } else {
                ps.setInt(1, puzzleId);
                ps.setInt(2, limit);
                ps.setInt(3, offset);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserDto user = new UserDto(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),  rs.getString("image"), rs.getString("role"), rs.getTimestamp("user_created_at"));

                    Review review = new Review();
                    review.setId(rs.getInt("review_id"));
                    review.setPuzzleId(rs.getInt("puzzle_id"));
                    review.setUserId(rs.getInt("user_id"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewText(rs.getString("review_text"));
                    review.setCreatedAt(rs.getTimestamp("review_created_at"));
                    review.setUser(user);
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public void saveReview(Review review) {
        String sql = "INSERT INTO reviews (puzzle_id, user_id, rating, review_text) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, review.getPuzzleId());
            preparedStatement.setInt(2, review.getUserId());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getReviewText());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            throw new RuntimeException("Error saving review to the database", e);
        }
    }

    @Override
    public Review getReviewById(Integer id) {
        Review review = null;
        String sql = "SELECT * FROM reviews WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    review = new Review();

                    review.setId(rs.getInt("id"));
                    review.setPuzzleId(rs.getInt("puzzle_id"));
                    review.setUserId(rs.getInt("user_id"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewText(rs.getString("review_text"));
                    review.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public Review removeReviewById(Integer id) {
        Review reviewToDelete = getReviewById(id);

        if (reviewToDelete == null) {
            return null;
        }

        String sql = "DELETE FROM reviews WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            return reviewToDelete;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not delete review with id " + id, e);
        }
    }
}
