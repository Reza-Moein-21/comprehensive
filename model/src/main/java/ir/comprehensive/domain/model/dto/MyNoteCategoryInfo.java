package ir.comprehensive.domain.model.dto;

public record MyNoteCategoryInfo(
        int totalCount,
        int inProgressCount,
        int doneCount,
        int stoppedCount
) {
}
