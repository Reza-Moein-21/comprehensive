package ir.comprehensive.entity.enums;

import ir.comprehensive.entity.base.BaseEnum;
import ir.comprehensive.utils.MessageUtils;

public enum ProductStatusEnum implements BaseEnum {
    UNKNOWN(MessageUtils.Message.UNKNOWN), RECEIVED(MessageUtils.Message.RECEIVED), REJECTED(MessageUtils.Message.REJECTED),
    LOST(MessageUtils.Message.LOST)
    ;

    private String title;

    ProductStatusEnum(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
