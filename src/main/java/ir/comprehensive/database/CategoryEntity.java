package ir.comprehensive.database;

import ir.comprehensive.database.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class CategoryEntity extends BaseEntity<Long> {

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
