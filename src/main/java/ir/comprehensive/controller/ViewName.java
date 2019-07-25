package ir.comprehensive.controller;

import java.net.URL;

public enum ViewName {
    START("fxml/start.fxml"),
    HOME("fxml/home.fxml"),
    STORE_ROOM("fxml/storeRoom.fxml"),
    HUMAN_RESOURCE("fxml/humanresource/humanResource.fxml"),
    MY_NOTEBOOK("fxml/myNote.fxml"),

    ;
    private String viewPath;

    ViewName(String viewPath) {
        this.viewPath = viewPath;
    }

    public URL getViewUrl() {
        return getClass().getResource("/" + this.viewPath);
    }
}
