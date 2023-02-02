package ir.comprehensive.domain.model.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DescribableDomainModel<I extends Serializable> extends DomainModel<I> {
    private String title;
    private String description;
}
