package ir.comprehensive.service.project;


import ir.comprehensive.domain.model.dto.CalenderNoteStatus;

import java.time.LocalDate;
import java.util.List;

public interface MyNoteService {
    List<CalenderNoteStatus> getCalenderNoteStatuses(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId);
}
