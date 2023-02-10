package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.MyNoteCategoryModel;

public interface MyNoteCategoryDao extends DomainDao<MyNoteCategoryModel, Long> {
    long countByStatus(MyNoteCategoryStatusEnum status);
}
