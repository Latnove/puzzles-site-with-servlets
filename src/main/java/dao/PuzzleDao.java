package dao;

import dto.CatalogPuzzleDto;
import dto.PuzzleCountsDto;
import dto.PuzzleDto;
import entity.Puzzle;

import java.util.List;

public interface PuzzleDao {
    List<Puzzle> getCollectPuzzlesByUserId(Integer userId, int offset, int limit);
    List<Puzzle> getCreatedPuzzlesByUserId(Integer userId, int offset, int limit);
    double getRating(Integer puzzleId);
    List<CatalogPuzzleDto> findFilteredPuzzles(Integer pieceStart, Integer pieceEnd, Integer minRating,
                                               String[] categories, Integer offset, Integer limit);
    Puzzle createPuzzle(Puzzle puzzle);
    void addCompletionPuzzleByUserId(Integer puzzleId, Integer userId);
    Puzzle getPuzzleById(Integer id);
    Boolean isCollectPuzzleByUserId(Integer puzzleId, Integer userId);
    PuzzleCountsDto getPuzzleCounts();
    List<Puzzle> getPuzzlesByStatus(String[] status, Integer offset, Integer limit);
    void updateStatus(Integer puzzleId, String status);
    Puzzle removePuzzleById(Integer id);
}
