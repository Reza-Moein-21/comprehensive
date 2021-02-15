package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
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
@Table(name = "PRODUCT")
public class Product extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

}

