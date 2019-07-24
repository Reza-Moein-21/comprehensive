package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "TITLE", nullable = false, unique = true)
    String title;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "FAX")
    String fax;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "DESCRIPTION")
    String description;

}
