package ir.comprehensive.service.dto;

import java.time.LocalDate;

public record CalenderNoteStatus(
        LocalDate creationTime,
        Long activeCount,
        Long inActiveCount
) {
}
