package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE_TAG")
public class WarehouseTag extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;
}
