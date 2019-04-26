package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PRODUCT_DELIVERY")
public class ProductDelivery implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_DELIVERY_PERSON"), nullable = false)
    Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_DELIVERY_PRODUCT"), nullable = false)
    Product product;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "DELIVERY_DATE", nullable = false)
    LocalDate deliveryDate;

    @Column(name = "DESIRED_DATE", nullable = false)
    LocalDate desiredDate;

    @Column(name = "RECEIVED_DATE")
    LocalDate receivedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_STATE", length = 10)
    ProductState state;
}
