package ir.comprehensive.utils;

import com.jfoenix.controls.JFXSnackbar;
import ir.comprehensive.controller.StartController;
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
        snackbar.setPrefWidth(mainStack.getWidth() / 2);
        return snackbar;
    }

    public static void showSuccessMessage(String message) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("success-toast");
        snackbar.show(message, "X", MESSAGE_TIMEOUT, event -> snackbar.close());

    }

    public static void showErrorMessage(String message) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("error-toast");
        snackbar.show(message, "X", MESSAGE_TIMEOUT, event -> snackbar.close());
    }

    public static void showWarningMessage(String message) {
        JFXSnackbar snackbar = getSnackbar();
        snackbar.getStyleClass().add("warning-toast");
        snackbar.show(message, "X", MESSAGE_TIMEOUT, event -> snackbar.close());
    }

    @PostConstruct
    public void registerInstance() {
        instance = this;
    }
}
