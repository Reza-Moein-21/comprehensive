package ir.comprehensive.service.dto;

public record MyNoteCategoryInfo(
        int totalCount,
        int inProgressCount,
        int doneCount,
        int stoppedCount
) {
}
