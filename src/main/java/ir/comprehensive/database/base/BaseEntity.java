package ir.comprehensive.database.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected I id;
}
