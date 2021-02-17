package ir.comprehensive.database.entity;

import ir.comprehensive.database.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE_CATEGORY")
public class WarehouseCategoryEntity extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

}
