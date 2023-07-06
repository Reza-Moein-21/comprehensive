package ir.comprehensive.common.factory;

public interface ObjectFactory<T> {
    default T getBean() {
        return getBean(BeanType.SINGLETON);
    }

    T getBean(BeanType type);

}
