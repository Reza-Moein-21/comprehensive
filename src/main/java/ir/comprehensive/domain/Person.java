package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "FULL_NAME", nullable = false)
    String fullName;
}
