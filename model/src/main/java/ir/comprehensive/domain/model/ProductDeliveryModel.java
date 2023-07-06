package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.time.LocalDate;


public record ProductDeliveryModel(
        Long id,
        String title,
        String description,
        PersonModel person,
        WarehouseModel product,
        Long count,
        LocalDate deliveryDate,
        LocalDate desiredDate,
        LocalDate receivedDate,
        ProductStatusEnum status

) implements DescribableDomainModel<Long> {
}
