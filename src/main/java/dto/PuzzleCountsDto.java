package dto;

public class PuzzleCountsDto {
    private final int totalCount;
    private final int pendingCount;
    private final int activeCount;
    private final int rejectedCount;

    public PuzzleCountsDto(int totalCount, int pendingCount, int activeCount, int rejectedCount) {
        this.totalCount = totalCount;
        this.pendingCount = pendingCount;
        this.activeCount = activeCount;
        this.rejectedCount = rejectedCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }
}
