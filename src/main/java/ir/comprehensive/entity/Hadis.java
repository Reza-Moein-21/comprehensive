package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HADIS")
public class Hadis extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION")
    String description;

}
