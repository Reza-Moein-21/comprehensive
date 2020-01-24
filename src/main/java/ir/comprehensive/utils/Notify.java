package ir.comprehensive.utils;

import com.jfoenix.controls.JFXSnackbar;
import ir.comprehensive.controller.StartController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Notify {
    private static final long MESSAGE_TIMEOUT = 5000;

    private static Notify instance;

    @Autowired
    private StartController startController;

    private static JFXSnackbar getSnackbar() {
        StackPane mainStack = instance.startController.mainStack;
        JFXSnackbar snackbar = new JFXSnackbar(mainStack);
        applyFontStyle(snackbar.getPopupContainer());
        snackbar.setPrefWidth(mainStack.getWidth() / 2);
        return snackbar;
    }

    public static void showSuccessMessage(String message) {
        showSuccessMessage(message, MESSAGE_TIMEOUT);
    }

    public static void showSuccessMessage(String message, long timeOut) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("success-toast");
        snackbar.show(message, "X", timeOut, event -> snackbar.close());

    }

    public static void showErrorMessage(String message) {
        showErrorMessage(message, MESSAGE_TIMEOUT);
    }

    public static void showErrorMessage(String message, long timeOut) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("error-toast");
        snackbar.show(message, "X", timeOut, event -> snackbar.close());
    }

    public static void showWarningMessage(String message) {
        showWarningMessage(message, MESSAGE_TIMEOUT);
    }

    public static void showWarningMessage(String message, long timeOut) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("warning-toast");
        snackbar.show(message, "X", timeOut, event -> snackbar.close());
    }

    @PostConstruct
    public void registerInstance() {
        instance = this;
    }

    private static void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }

}
