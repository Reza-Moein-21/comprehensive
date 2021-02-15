package ir.comprehensive.entity.base;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseModel<T extends Serializable> implements Serializable {
    protected T id;
}
