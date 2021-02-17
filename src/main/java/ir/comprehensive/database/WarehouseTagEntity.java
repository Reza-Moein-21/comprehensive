package ir.comprehensive.database;

import ir.comprehensive.database.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE_TAG")
public class WarehouseTagEntity extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;
}
