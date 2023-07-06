package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

public record WarehouseTagModel(
        Long id,
        String title,
        String description

) implements DescribableDomainModel<Long> {
}
