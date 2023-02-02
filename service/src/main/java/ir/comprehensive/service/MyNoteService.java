package ir.comprehensive.service;

import ir.comprehensive.service.dto.CalenderNoteStatus;

import java.time.LocalDate;
import java.util.List;

public interface MyNoteService {
    List<CalenderNoteStatus> getCalenderNoteStatuses(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId);
}
