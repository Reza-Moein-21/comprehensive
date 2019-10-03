package ir.comprehensive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "WAREHOUSE")
public class Warehouse {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_WHOUSE_WHOUSE_CAT"), nullable = false)
    WarehouseCategory category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WAREHOUSE_TAG_ID", foreignKey = @ForeignKey(name = "FK_WHOUSE_WHOUSE_TAG"), nullable = false)
    List<WarehouseTag> tagList;

}
