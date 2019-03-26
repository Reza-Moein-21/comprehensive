package ir.comperhensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "NATIONAL_CODE", nullable = false)
    String nationalCode;

    @Column(name = "FIRST_NAME", nullable = false)
    String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    String lastName;

    @Column(name = "FATHER_NAME")
    String fatherName;

    @Column(name = "BIRTH_DATE")
    Date birthDate;
}
