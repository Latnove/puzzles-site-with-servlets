package service;

import com.cloudinary.Cloudinary;
import com.zaxxer.hikari.HikariDataSource;
import dao.PuzzleDao;
import dao.UserDao;
import dao.impl.PuzzleDaoImpl;
import dao.impl.UserDaoImpl;
import dto.*;
import entity.Puzzle;
import entity.User;

import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleService {
    private final PuzzleDao puzzleDao;
    private final UserService userService;
    private final Cloudinary cloudinary;
    private final CategoryService categoryService;

    public PuzzleService(HikariDataSource dataSource, Cloudinary cloudinary, CategoryService categoryService, UserService userService) {
        this.puzzleDao = new PuzzleDaoImpl(dataSource);
        this.cloudinary = cloudinary;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public List<CollectPuzzleDto> getCollectPuzzles(Integer userId, Integer offset, Integer limit) {
        List<Puzzle> puzzleEntities = puzzleDao.getCollectPuzzlesByUserId(userId, offset, limit);

        return puzzleEntities.stream()
                .map((el) -> new CollectPuzzleDto(
                el.getId(),
                el.getTitle(),
                el.getImageUrl(),
                el.getPieceCols(),
                el.getPieceRows(),
                calculateRatingForPuzzle(el.getId()),
                el.getCreatedAt().getTime()))
                .collect(Collectors.toList());
    }

    public Boolean isCollectPuzzleByUserId(Integer puzzleId, Integer userId) {
        return puzzleDao.isCollectPuzzleByUserId(puzzleId, userId);
    }

    public List<CreatedPuzzleDto> getCreatedPuzzles(Integer userId, Integer offset, Integer limit) {
        List<Puzzle> puzzleEntities = puzzleDao.getCreatedPuzzlesByUserId(userId, offset, limit);

        return puzzleEntities.stream()
                .map(el -> new CreatedPuzzleDto(
                        el.getId(),
                        el.getTitle(),
                        el.getImageUrl(),
                        el.getPieceCols(),
                        el.getPieceRows(),
                        el.getStatus(),
                        el.getCreatedAt().getTime()
                ))
                .collect(Collectors.toList());
    }

    public List<CatalogPuzzleDto> getCatalogPuzzles(Integer pieceStart, Integer pieceEnd, Integer minRating, String[] categories, Integer offset, Integer limit) {
        List<CatalogPuzzleDto> puzzles = puzzleDao.findFilteredPuzzles(pieceStart, pieceEnd, minRating, categories, offset, limit);

        return puzzles;
    }

    public Puzzle createPuzzle(CreatePuzzleDto createPuzzleDto, Part part) throws IOException {
        int available = part.getInputStream().available();
        byte[] bytes = new byte[available];
        part.getInputStream().read(bytes);
        String imageUrl = (String) cloudinary.uploader().upload(bytes, new HashMap<>()).get("secure_url");

        Puzzle puzzle = new Puzzle();
        puzzle.setTitle(createPuzzleDto.getTitle());
        puzzle.setImageUrl(imageUrl);
        puzzle.setPieceCols(createPuzzleDto.getPieceCols());
        puzzle.setPieceRows(createPuzzleDto.getPieceRows());
        puzzle.setUserId(createPuzzleDto.getUserId());
        puzzle.setCategories(createPuzzleDto.getCategories());

        return puzzleDao.createPuzzle(puzzle);
    }

    public PuzzleDto getPuzzleById(Integer puzzleId) {
        Puzzle puzzle = puzzleDao.getPuzzleById(puzzleId);
        if (puzzle == null) {
            return null;
        }

        puzzle.setCategories(categoryService.getCategoriesByPuzzleId(puzzleId));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        PuzzleDto puzzleDto = new PuzzleDto();
        puzzleDto.setId(puzzle.getId());
        puzzleDto.setTitle(puzzle.getTitle());
        puzzleDto.setImageUrl(puzzle.getImageUrl());
        puzzleDto.setPieceCols(puzzle.getPieceCols());
        puzzleDto.setPieceRows(puzzle.getPieceRows());
        puzzleDto.setStatus(puzzle.getStatus());
        puzzleDto.setUserId(puzzle.getUserId());
        puzzleDto.setModeratorId(puzzle.getModeratorId());
        puzzleDto.setCategories(puzzle.getCategories());
        puzzleDto.setCreatedAt(sdf.format(puzzle.getCreatedAt()));
        puzzleDto.setRating(calculateRatingForPuzzle(puzzleId));
        return puzzleDto;
    }

    public void addCompletionPuzzleByUserId(Integer puzzleId, Integer userId) {
        puzzleDao.addCompletionPuzzleByUserId(puzzleId, userId);
    }

    public PuzzleCountsDto getPuzzleCounts() {
        return puzzleDao.getPuzzleCounts();
    }

    public List<PuzzleDto> getPuzzlesByStatus(String[] status, Integer offset, Integer limit) {
        List<Puzzle> puzzles = puzzleDao.getPuzzlesByStatus(status, offset, limit);

        List<PuzzleDto> puzzleDtos = new ArrayList<>();

        for (Puzzle puzzle : puzzles) {
            PuzzleDto puzzleDto = new PuzzleDto();

            puzzleDto.setId(puzzle.getId());
            puzzleDto.setTitle(puzzle.getTitle());
            puzzleDto.setImageUrl(puzzle.getImageUrl());
            puzzleDto.setPieceCols(puzzle.getPieceCols());
            puzzleDto.setPieceRows(puzzle.getPieceRows());
            puzzleDto.setStatus(puzzle.getStatus());
            puzzleDto.setUserId(puzzle.getUserId());
            puzzleDto.setModeratorId(puzzle.getModeratorId());
            puzzleDto.setCategories(categoryService.getCategoriesByPuzzleId(puzzle.getId()));
            puzzleDto.setUser(userService.getById(puzzle.getUserId()));
            puzzleDto.setRating(calculateRatingForPuzzle(puzzle.getId()));
            puzzleDto.setCreatedAt(puzzle.getCreatedAt().toString());

            puzzleDtos.add(puzzleDto);
        }

        return puzzleDtos;
    }

    public void updateStatus(Integer puzzleId, String status) {
        PuzzleDto puzzleDto = getPuzzleById(puzzleId);
        if (puzzleDto != null && (new ArrayList<>(List.of("active", "pending", "rejected"))).contains(status)) {
            puzzleDao.updateStatus(puzzleId, status);
        }
    }

    public Puzzle removePuzzleById(Integer puzzleId) {
        Puzzle puzzle = puzzleDao.getPuzzleById(puzzleId);

        if (puzzle != null) {
            return puzzleDao.removePuzzleById(puzzleId);
        }

        return null;
    }

    private double calculateRatingForPuzzle(Integer puzzleId) {
        return puzzleDao.getRating(puzzleId);
    }
}
