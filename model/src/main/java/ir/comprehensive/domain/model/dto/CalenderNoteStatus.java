package ir.comprehensive.domain.model.dto;

import java.time.LocalDate;

public record CalenderNoteStatus(
        LocalDate creationTime,
        Integer activeCount,
        Integer inActiveCount
) {
}
