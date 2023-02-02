package ir.comprehensive.domain.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class DomainModel<I extends Serializable> implements Serializable {
    @EqualsAndHashCode.Include
    protected I id;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedTime;
    private int version;

}
