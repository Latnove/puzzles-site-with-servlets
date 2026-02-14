package dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dao.PuzzleDao;
import dto.CatalogPuzzleDto;
import dto.PuzzleCountsDto;
import dto.PuzzleDto;
import entity.Category;
import entity.Puzzle;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PuzzleDaoImpl implements PuzzleDao {
    private final HikariDataSource dataSource;

    public PuzzleDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Puzzle> getCollectPuzzlesByUserId(Integer userId, int offset, int limit) {
        String sql = "SELECT p.*, pc.created_at AS pc_created_at FROM puzzles p JOIN puzzle_completions pc ON p.id = pc.puzzle_id WHERE pc.user_id = ? ORDER BY pc.created_at DESC LIMIT ? OFFSET ?;";
        List<Puzzle> collectedPuzzles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Puzzle puzzle = new Puzzle();
                    puzzle.setId(resultSet.getInt("id"));
                    puzzle.setTitle(resultSet.getString("title"));
                    puzzle.setImageUrl(resultSet.getString("image_url"));
                    puzzle.setPieceCols(resultSet.getInt("piece_cols"));
                    puzzle.setPieceRows(resultSet.getInt("piece_rows"));
                    puzzle.setStatus(resultSet.getString("status"));
                    puzzle.setUserId(resultSet.getInt("user_id"));
                    puzzle.setModeratorId(resultSet.getInt("moderator_id"));
                    puzzle.setCreatedAt(resultSet.getTimestamp("pc_created_at"));
                    puzzle.setStatus(resultSet.getString("status"));

                    collectedPuzzles.add(puzzle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении собранных пазлов для пользователя: " + userId, e);
        }

        return collectedPuzzles;
    }

    @Override
    public List<Puzzle> getCreatedPuzzlesByUserId(Integer userId, int offset, int limit) {
        String sql = "SELECT * FROM puzzles WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        List<Puzzle> createdPuzzles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Puzzle puzzle = new Puzzle();
                    puzzle.setId(resultSet.getInt("id"));
                    puzzle.setTitle(resultSet.getString("title"));
                    puzzle.setImageUrl(resultSet.getString("image_url"));
                    puzzle.setPieceCols(resultSet.getInt("piece_cols"));
                    puzzle.setPieceRows(resultSet.getInt("piece_rows"));
                    puzzle.setStatus(resultSet.getString("status"));
                    puzzle.setUserId(resultSet.getInt("user_id"));
                    puzzle.setModeratorId(resultSet.getInt("moderator_id"));
                    puzzle.setCreatedAt(resultSet.getTimestamp("created_at"));
                    puzzle.setStatus(resultSet.getString("status"));

                    createdPuzzles.add(puzzle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении созданных пазлов для пользователя: " + userId, e);
        }

        return createdPuzzles;
    }

    @Override
    public double getRating(Integer puzzleId) {
        String sql = "SELECT COALESCE(AVG(rating), 0.0) AS average_rating FROM reviews WHERE puzzle_id = ?";
        double averageRating = 0.0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, puzzleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    averageRating = resultSet.getDouble("average_rating");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при вычислении рейтинга для пазла: " + puzzleId, e);
        }

        if (averageRating == 0.0) {
            return 0.0;
        }

        BigDecimal bd = new BigDecimal(Double.toString(averageRating));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public List<CatalogPuzzleDto> findFilteredPuzzles(Integer pieceStart, Integer pieceEnd, Integer minRating,
                                                      String[] categories, Integer offset, Integer limit) {
        List<CatalogPuzzleDto> resultList = new ArrayList<>();
        StringBuilder innerSql = new StringBuilder(
                "SELECT p.id, p.title, p.image_url, p.piece_cols, p.piece_rows, p.status, p.user_id, p.moderator_id, p.created_at, " +
                        "COALESCE((SELECT AVG(r.rating) FROM reviews r WHERE r.puzzle_id = p.id), 0.0) as average_rating, " +
                        "(SELECT COUNT(*) FROM puzzle_completions c WHERE c.puzzle_id = p.id) as completions_count " +
                        "FROM puzzles p WHERE p.status = 'active'"
        );

        List<Object> params = new ArrayList<>();

        if (pieceStart != null) {
            innerSql.append(" AND (p.piece_cols * p.piece_rows) >= ?");
            params.add(pieceStart);
        }
        if (pieceEnd != null) {
            innerSql.append(" AND (p.piece_cols * p.piece_rows) <= ?");
            params.add(pieceEnd);
        }
        if (categories != null && categories.length > 0) {
            String placeholders = String.join(",", Collections.nCopies(categories.length, "?"));
            innerSql.append(" AND EXISTS (SELECT 1 FROM puzzle_categories pc " +
                    "JOIN categories cat ON pc.category_id = cat.id " +
                    "WHERE pc.puzzle_id = p.id AND cat.name IN (").append(placeholders).append("))");
            params.addAll(Arrays.asList(categories));
        }

        StringBuilder finalSql = new StringBuilder("SELECT * FROM (").append(innerSql).append(") as filtered_puzzles WHERE 1=1");

        if (minRating != null) {
            finalSql.append(" AND average_rating >= ?");
            params.add(minRating);
        }

        finalSql.append(" ORDER BY created_at DESC");

        if (limit != null) {
            finalSql.append(" LIMIT ?");
            params.add(limit);
        }
        if (offset != null) {
            finalSql.append(" OFFSET ?");
            params.add(offset);
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(finalSql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    CatalogPuzzleDto dto = new CatalogPuzzleDto();
                    dto.setId(resultSet.getInt("id"));
                    dto.setTitle(resultSet.getString("title"));
                    dto.setImageUrl(resultSet.getString("image_url"));
                    dto.setPieceCols(resultSet.getInt("piece_cols"));
                    dto.setPieceRows(resultSet.getInt("piece_rows"));
                    dto.setUserId(resultSet.getInt("user_id"));

                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    if (createdAt != null) {
                        dto.setCreatedAt(createdAt.getTime());
                    }

                    BigDecimal averageRating = resultSet.getBigDecimal("average_rating");

                    if (averageRating == null) {
                        averageRating = BigDecimal.ZERO;
                    }

                    averageRating = averageRating.setScale(2, RoundingMode.HALF_UP);

                    dto.setRating(averageRating.doubleValue());

                    dto.setCompletionsCount(resultSet.getInt("completions_count"));

                    resultList.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public Boolean isCollectPuzzleByUserId(Integer puzzleId, Integer userId) {
        String sql = "SELECT DISTINCT * FROM puzzle_completions WHERE puzzle_id = ? AND user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, puzzleId);
            preparedStatement.setInt(2, userId);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении собранных пазлов для пользователя: " + userId, e);
        }

        return false;
    }

    @Override
    public Puzzle createPuzzle(Puzzle puzzle) {
        String insertPuzzleSql = "INSERT INTO puzzles (title, image_url, piece_cols, piece_rows, user_id, status) VALUES (?, ?, ?, ?, ?, 'pending');";

        String insertPuzzleCategoriesSql = "INSERT INTO puzzle_categories (puzzle_id, category_id) VALUES (?, ?);";

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement psPuzzle = conn.prepareStatement(insertPuzzleSql, Statement.RETURN_GENERATED_KEYS)) {
                psPuzzle.setString(1, puzzle.getTitle());
                psPuzzle.setString(2, puzzle.getImageUrl());
                psPuzzle.setInt(3, puzzle.getPieceCols());
                psPuzzle.setInt(4, puzzle.getPieceRows());
                psPuzzle.setInt(5, puzzle.getUserId());

                int affectedRows = psPuzzle.executeUpdate();

                if (affectedRows == 0) {
                    System.out.println("Creating puzzle failed, no rows affected.");
                    return null;
                }

                try (ResultSet generatedKeys = psPuzzle.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        puzzle.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Creating puzzle failed, no ID obtained.");
                        return null;
                    }
                }
            }

            if (puzzle.getCategories() != null && !puzzle.getCategories().isEmpty()) {
                try (PreparedStatement psCategories = conn.prepareStatement(insertPuzzleCategoriesSql)) {
                    for (Category category : puzzle.getCategories()) {
                        psCategories.setInt(1, puzzle.getId());
                        psCategories.setInt(2, category.getId());
                        psCategories.addBatch();
                    }
                    psCategories.executeBatch();
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to create puzzle.");
            e.printStackTrace();
            return null;
        }

        return puzzle;
    }

    @Override
    public Puzzle getPuzzleById(Integer id) {
        String sql = "SELECT id, title, image_url, piece_cols, piece_rows, status, user_id, moderator_id, created_at FROM puzzles WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Puzzle puzzle = new Puzzle();
                    puzzle.setId(rs.getInt("id"));
                    puzzle.setTitle(rs.getString("title"));
                    puzzle.setImageUrl(rs.getString("image_url"));
                    puzzle.setPieceCols(rs.getInt("piece_cols"));
                    puzzle.setPieceRows(rs.getInt("piece_rows"));
                    puzzle.setStatus(rs.getString("status"));
                    puzzle.setUserId(rs.getInt("user_id"));

                    Object moderatorIdObj = rs.getObject("moderator_id");
                    if (moderatorIdObj != null) {
                        puzzle.setModeratorId((Integer) moderatorIdObj);
                    }

                    puzzle.setCreatedAt(rs.getTimestamp("created_at"));

                    return puzzle;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addCompletionPuzzleByUserId(Integer puzzleId, Integer userId) {
        String sql = "INSERT INTO puzzle_completions (puzzle_id, user_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, puzzleId);
            if (userId == null) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, userId);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while adding puzzle completion for user: " + userId, e);
        }
    }

    @Override
    public PuzzleCountsDto getPuzzleCounts() {
        String sql = "SELECT COUNT(*) AS total_count, COUNT(CASE WHEN status = 'pending' THEN 1 END) AS pending_count, COUNT(CASE WHEN status = 'active' THEN 1 END) AS active_count, COUNT(CASE WHEN status = 'rejected' THEN 1 END) AS rejected_count FROM puzzles;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new PuzzleCountsDto(
                        rs.getInt("total_count"),
                        rs.getInt("pending_count"),
                        rs.getInt("active_count"),
                        rs.getInt("rejected_count")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching puzzle counts from database", e);
        }

        return new PuzzleCountsDto(0, 0, 0, 0);
    }

    @Override
    public List<Puzzle> getPuzzlesByStatus(String[] statuses, Integer offset, Integer limit) {
        if (statuses == null || statuses.length == 0) {
            return Collections.emptyList();
        }

        List<Puzzle> puzzles = new ArrayList<>();

        String placeholders = String.join(",", Collections.nCopies(statuses.length, "?"));

        String sql = "SELECT * FROM puzzles WHERE status IN (%s) ORDER BY created_at LIMIT ? OFFSET ?;".formatted(placeholders);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            for (String status : statuses) {
                ps.setString(paramIndex++, status);
            }

            ps.setInt(paramIndex++, limit);
            ps.setInt(paramIndex, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Puzzle puzzle = new Puzzle();
                puzzle.setId(rs.getInt("id"));
                puzzle.setTitle(rs.getString("title"));
                puzzle.setImageUrl(rs.getString("image_url"));
                puzzle.setPieceCols(rs.getInt("piece_cols"));
                puzzle.setPieceRows(rs.getInt("piece_rows"));
                puzzle.setStatus(rs.getString("status"));
                puzzle.setUserId(rs.getInt("user_id"));
                puzzle.setModeratorId(rs.getInt("moderator_id"));
                puzzle.setCreatedAt(rs.getTimestamp("created_at"));

                puzzles.add(puzzle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении пазлов по статусу", e);
        }

        return puzzles;
    }

    @Override
    public void updateStatus(Integer puzzleId, String status) {
        String sql = "UPDATE puzzles SET status = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, puzzleId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка обновления", e);
        }
    }

    public Puzzle removePuzzleById(Integer puzzleId) {
        Puzzle puzzle = getPuzzleById(puzzleId);

        String sql = "DELETE FROM puzzles WHERE id = ?";

        if (puzzle == null) {
            return null;
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, puzzleId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return puzzle;
    }
}