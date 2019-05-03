package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME", nullable = false)
    String name;

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

    @ManyToMany(mappedBy = "categories")
    Set<Person> people = new HashSet<>();
}
