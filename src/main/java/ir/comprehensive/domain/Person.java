package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "PERSON", uniqueConstraints = @UniqueConstraint(columnNames = {"FIRST_NAME", "LAST_NAME"}))
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    String lastName;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "PERSON_CATEGORY",
            joinColumns = {@JoinColumn(name = "PERSON_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    Set<Category> categories = new HashSet<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
