package ir.comprehensive.jfxapp.component.jfxactivecombo;

import ir.comprehensive.jfxapp.utils.MessageUtils;

public enum JFXActiveValue {
    ACTIVE(MessageUtils.Message.ACTIVE), INACTIVE(MessageUtils.Message.INACTIVE), NONE(MessageUtils.Message.NONE);

    private String title;

    JFXActiveValue(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
