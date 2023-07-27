package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.DescribableDomainDao;
import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.MyNoteCategoryModel;

public interface MyNoteCategoryDao extends DescribableDomainDao<MyNoteCategoryModel, Long> {
    long countByStatus(MyNoteCategoryStatusEnum status);
}