package ir.comprehensive.domain.model.base;

import java.io.Serializable;

public interface DescribableDomainModel<I extends Serializable> extends DomainModel<I> {
    String title();
    String description();
}
