package ir.comprehensive.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected T id;
}
