package service;

import com.zaxxer.hikari.HikariDataSource;
import dao.ReviewDao;
import dao.impl.ReviewDaoImpl;
import dto.ReviewDto;
import entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewService {
    private final ReviewDao reviewDao;

    public ReviewService(HikariDataSource dataSource) {
        this.reviewDao = new ReviewDaoImpl(dataSource);
    }

    public List<ReviewDto> getAllReviewsByPuzzleId(Integer puzzleId) {
        List<Review> reviews = reviewDao.getAllReviewsByPuzzleId(puzzleId);
        List<ReviewDto> reviewDto = reviews.stream().map(el -> new ReviewDto(el)
        ).collect(Collectors.toList());
        return reviewDto;
    }

    public ReviewDto getPuzzleReviewByUserId(Integer puzzleId, Integer userId) {
        Review review = reviewDao.getPuzzleReviewByUserId(puzzleId, userId);
        if (review == null) {
            return null;
        }
        return new ReviewDto(review);
    }

    public List<ReviewDto> getReviewsByPuzzleIdIgnoredUserId(Integer puzzleId, Integer userId, Integer offset, Integer limit) {
        List<Review> reviews = reviewDao.getReviewsByPuzzleIdIgnoredUserId(puzzleId, userId, offset, limit);
        List<ReviewDto> reviewDto = reviews.stream().map(el -> new ReviewDto(el)
        ).collect(Collectors.toList());
        return reviewDto;
    }

    public void saveReview(ReviewDto reviewDto) {
        if (getPuzzleReviewByUserId(reviewDto.getPuzzleId(), reviewDto.getUserId()) == null) {
            Review review = new Review();
            review.setPuzzleId(reviewDto.getPuzzleId());
            review.setUserId(reviewDto.getUserId());
            review.setRating(reviewDto.getRating());
            review.setReviewText(reviewDto.getReviewText());

            reviewDao.saveReview(review);
        }
    }

    public ReviewDto removeReviewById(Integer reviewId) {
        Review review = reviewDao.removeReviewById(reviewId);

        if (review == null) {
            return null;
        }

        return new ReviewDto(review);
    }

    public ReviewDto getReviewById(Integer reviewId) {
        Review review = reviewDao.getReviewById(reviewId);

        if (review == null) {
            return null;
        }

        return new ReviewDto(review);
    }
}
