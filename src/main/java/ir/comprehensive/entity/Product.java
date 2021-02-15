package ir.comprehensive.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "TITLE", nullable = false)
    String title;

}

