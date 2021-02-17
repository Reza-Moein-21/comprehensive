package ir.comprehensive.database.entity;

import ir.comprehensive.database.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE")
public class WarehouseEntity extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;
    @Column(name = "CODE", nullable = false)
    String code;
    @Column(name = "COMPANY_NAME")
    String companyName;
    @Column(name = "PRODUCER_NAME")
    String producerName;
    @Column(name = "COUNT", nullable = false)
    Long count;
    @Column(name = "DESCRIPTION")
    String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "WAREHOUSE_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_WHOUSE_WHOUSE_CAT"))
    WarehouseCategoryEntity category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "WAREHOUSE_WAREHOUSE_TAG",
            joinColumns = @JoinColumn(name = "WAREHOUSE_ID", foreignKey = @ForeignKey(name = "FK_W_TAG")),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "FK_TAG_W")))
    List<WarehouseTagEntity> tagList = new ArrayList<>();


}
