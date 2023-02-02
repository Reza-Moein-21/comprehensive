package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;
import ir.comprehensive.domain.model.base.DomainModel;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ProductDeliveryModel extends DescribableDomainModel<Long> {

    private PersonModel person;

    private WarehouseModel product;

    private Long count;

    private LocalDate deliveryDate;

    private LocalDate desiredDate;

    private LocalDate receivedDate;

    private ProductStatusEnum status;
}
