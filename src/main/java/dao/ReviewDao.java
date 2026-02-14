package dao;

import entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getAllReviewsByPuzzleId(Integer puzzleId);
    List<Review> getReviewsByPuzzleIdIgnoredUserId(Integer puzzleId, Integer userId, Integer offset, Integer limit);
    Review getPuzzleReviewByUserId(Integer puzzleId, Integer userId);
    Review getReviewById(Integer id);
    void saveReview(Review review);
    Review removeReviewById(Integer id);
}
