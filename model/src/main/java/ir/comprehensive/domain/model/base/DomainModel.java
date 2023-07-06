package ir.comprehensive.domain.model.base;

import java.io.Serializable;

public interface DomainModel<I extends Serializable> {
    I id();
}
