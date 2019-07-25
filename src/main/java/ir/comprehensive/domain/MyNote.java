package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "MY_NOTE")
public class MyNote {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "CREATION_DATE", nullable = false)
    LocalDate creationDate;

    @Column(name = "PRIORITY", nullable = false)
    Double priority;

    @Column(name = "IS_ACTIVE")
    Boolean isActive;
}
