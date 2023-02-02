package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class WarehouseTagModel extends DescribableDomainModel<Long> {
}
