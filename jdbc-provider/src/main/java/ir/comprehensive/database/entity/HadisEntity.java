package ir.comprehensive.database.entity;

import ir.comprehensive.database.entity.base.BaseEntity;
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
@Table(name = "HADIS")
public class HadisEntity extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION")
    String description;

}
