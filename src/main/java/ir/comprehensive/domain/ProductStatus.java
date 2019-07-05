package ir.comprehensive.domain;

import ir.comprehensive.domain.extra.BaseEnum;
import ir.comprehensive.utils.MessageUtils;

public enum ProductStatus implements BaseEnum {
    UNKNOWN(MessageUtils.Message.UNKNOWN), RECEIVED(MessageUtils.Message.RECEIVED), REJECTED(MessageUtils.Message.REJECTED),
    LOST(MessageUtils.Message.LOST)
    ;

    private String title;

    ProductStatus(String title) {
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
