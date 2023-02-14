package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.model.MyNoteModel;
import ir.comprehensive.domain.model.dto.CalenderNoteStatus;

import java.time.LocalDate;
import java.util.List;

public interface MyNoteDao extends DomainDao<MyNoteModel, Long> {
    List<CalenderNoteStatus> findNumberOfByDate(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId);

}
