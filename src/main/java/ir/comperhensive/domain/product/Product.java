package ir.comperhensive.domain.product;

import ir.comperhensive.domain.product.state.ProductState;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "DELIVERY_DATE")
    Date deliveryDate;

    @Column(name = "DESIRED_DATE")
    Date desiredDate;

    @Column(name = "RECEIVED_DATE")
    Date receivedDate;

    @Enumerated(EnumType.STRING)
    ProductState state;
}

