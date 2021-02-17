package ir.comprehensive.database.entity;

import ir.comprehensive.database.base.BaseEntity;
import ir.comprehensive.database.entity.CategoryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSON", uniqueConstraints = @UniqueConstraint(columnNames = {"FIRST_NAME", "LAST_NAME"}))
public class PersonEntity extends BaseEntity<Long> {

    @Column(name = "FIRST_NAME", nullable = false)
    String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    String lastName;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "DESCRIPTION", length = Integer.MAX_VALUE)
    String description;

    @Transient
    String fullName = this.firstName + " " + this.lastName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_CATEGORY",
            joinColumns = {@JoinColumn(name = "PERSON_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    Set<CategoryEntity> categories = new HashSet<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
