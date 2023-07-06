package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

public record CategoryModel(
        Long id,
        String title,
        String description,
        String phoneNumber,
        String fax,
        String email,
        String address

) implements DescribableDomainModel<Long> {
}
