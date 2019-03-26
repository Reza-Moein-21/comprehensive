package ir.comperhensive.domain.product.state;

import ir.comperhensive.domain.baseenum.BaseEnum;

import static ir.comperhensive.utils.MessageUtils.getMessage;

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
