package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.util.Set;


public record PersonModel(
        Long id,
        String title,
        String description,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String fullName,
        Set<CompanyModel> categories

) implements DescribableDomainModel<Long> {
    // TODO Consider to remove this and use title filed instead
    @Override
    public String fullName() {
        return this.title;
    }
}
