package ir.comprehensive.controller;

import ir.comprehensive.utils.MessageUtils;

import java.net.URL;

public enum ViewName {
    START("fxml/start.fxml", MessageUtils.Message.START_PAGE),
    HOME("fxml/home.fxml", MessageUtils.Message.HOME),
    STORE_ROOM("fxml/storeRoom.fxml", MessageUtils.Message.STOREROOM),
    HUMAN_RESOURCE("fxml/humanresource/humanResource.fxml", MessageUtils.Message.HUMAN_RESOURCE),
    MY_NOTEBOOK("fxml/myNote.fxml", MessageUtils.Message.MY_NOTE),
    MY_NOTE_CATEGORY("fxml/MyNoteCategory.fxml", MessageUtils.Message.MY_NOTE_CATEGORY),

    ;
    private String viewPath;
    private String title;

    ViewName(String viewPath, String title) {
        this.viewPath = viewPath;
        this.title = title;
    }

    public URL getViewUrl() {
        return getClass().getResource("/" + this.viewPath);
    }

    public String getTitle() {
        return title;
    }
}
