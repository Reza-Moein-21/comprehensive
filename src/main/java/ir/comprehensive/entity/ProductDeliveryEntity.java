package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_DELIVERY")
public class ProductDeliveryEntity extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_DELIVERY_PERSON"), nullable = false)
    PersonEntity person;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_DELIVERY_PRODUCT"), nullable = false)
    WarehouseEntity product;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "COUNT")
    Long count;

    @Column(name = "DELIVERY_DATE", nullable = false)
    LocalDate deliveryDate;

    @Column(name = "DESIRED_DATE")
    LocalDate desiredDate;

    @Column(name = "RECEIVED_DATE")
    LocalDate receivedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    ProductStatusEnum status;
}
