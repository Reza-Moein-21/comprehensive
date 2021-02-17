package ir.comprehensive.database.model;

import ir.comprehensive.database.enums.ProductStatusEnum;
import ir.comprehensive.database.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeliveryModel extends BaseModel<Long> {

    private PersonModel person;

    private WarehouseModel product;

    private String description;

    private Long count;

    private LocalDate deliveryDate;

    private LocalDate desiredDate;

    private LocalDate receivedDate;

    private ProductStatusEnum status;
}
