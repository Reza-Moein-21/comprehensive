package ir.comprehensive.component.jfxactivecombo;

import ir.comprehensive.utils.MessageUtils;

public enum JFXActiveValue {
    IN_PROGRESS(MessageUtils.Message.IN_PROGRESS), DONE(MessageUtils.Message.DONE), NONE(MessageUtils.Message.NONE);

    private String title;

    JFXActiveValue(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
