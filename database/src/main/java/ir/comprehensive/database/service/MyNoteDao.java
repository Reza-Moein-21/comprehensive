package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.model.MyNoteModel;
import ir.comprehensive.domain.model.dto.CalenderNoteStatus;

import java.time.LocalDate;
import java.util.List;

public interface MyNoteDao extends DescribableDomainDao<MyNoteModel, Long> {
    List<CalenderNoteStatus> findNumberOfByDate(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId);

}
