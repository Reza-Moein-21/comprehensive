package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
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
