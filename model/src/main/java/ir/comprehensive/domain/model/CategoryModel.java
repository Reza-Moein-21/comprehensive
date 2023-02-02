package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CategoryModel extends DescribableDomainModel<Long> {
    private String phoneNumber;

    private String fax;

    private String email;

    private String address;

}
