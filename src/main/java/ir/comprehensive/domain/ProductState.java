package ir.comprehensive.domain;

import static ir.comprehensive.utils.MessageUtils.getMessage;

public enum ProductState implements BaseEnum {
    RECEIVED(getMessage("received")), REJECTED(getMessage("rejected"));

    private String title;

    ProductState(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

}
