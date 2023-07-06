package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

public record ProductModel(
        Long id,
        String title,
        String description

) implements DescribableDomainModel<Long> {
}

