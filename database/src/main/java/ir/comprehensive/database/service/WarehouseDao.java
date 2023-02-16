package ir.comprehensive.database.service;

import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.model.WarehouseModel;

import java.util.List;

public interface WarehouseDao extends DescribableDomainDao<WarehouseModel, Long> {
    PageModel<WarehouseModel> findAllByCodeOrTitle(String codeTitle, PageRequestModel pageRequest) throws SearchingException;

    List<Long> findAllIdByTagId(Long tagId);
}
