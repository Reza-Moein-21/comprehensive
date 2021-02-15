package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEnum;
import ir.comprehensive.utils.MessageUtils;

public enum MyNoteCategoryStatusEnum implements BaseEnum {
    IN_PROGRESS(MessageUtils.Message.IN_PROGRESS), DONE(MessageUtils.Message.DONE), STOPPED(MessageUtils.Message.STOPPED),
    ;

    private String title;

    MyNoteCategoryStatusEnum(String title) {
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
