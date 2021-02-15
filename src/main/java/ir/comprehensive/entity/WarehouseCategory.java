package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE_CATEGORY")
public class WarehouseCategory extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

}
