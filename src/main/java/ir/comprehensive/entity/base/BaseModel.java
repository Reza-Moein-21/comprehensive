package ir.comprehensive.entity.base;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseModel<I extends Serializable> implements Serializable {
    protected I id;
}
