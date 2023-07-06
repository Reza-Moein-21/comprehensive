package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.util.List;


public record WarehouseModel(
        Long id,
        String title,
        String description,
        String code,
        String companyName,
        String producerName,
        Long count,
        WarehouseCategoryModel category,
        List<WarehouseTagModel> tagList

) implements DescribableDomainModel<Long> {
    @Override
    public List<WarehouseTagModel> tagList() {
        if (tagList == null)
            return List.of();
        return tagList;
    }
}
