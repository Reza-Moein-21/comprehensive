package ir.comprehensive.domain;

import ir.comprehensive.utils.MessageUtils;

public enum ProductStatus implements BaseEnum {
    NEW_PRODUCT(MessageUtils.Message.NEW_PRODUCT), RECEIVED(MessageUtils.Message.RECEIVED), REJECTED(MessageUtils.Message.REJECTED);

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
