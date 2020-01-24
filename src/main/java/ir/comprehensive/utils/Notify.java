package ir.comprehensive.utils;

import com.jfoenix.controls.JFXSnackbar;
import ir.comprehensive.controller.StartController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    private static final double NOTIFICATION_HEIGHT = ScreenUtils.getActualSize(400);
    private static final double NOTIFICATION_WIDTH = ScreenUtils.getActualSize(800);


    private static Stage notificationStage;


    public static void showMessage(String message, AzanType azanType) {
        if (notificationStage == null) {
            notificationStage = new Stage();
            notificationStage.initStyle(StageStyle.UNDECORATED);
        }
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        notificationStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - NOTIFICATION_WIDTH);
        notificationStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - NOTIFICATION_HEIGHT);
        notificationStage.setWidth(NOTIFICATION_WIDTH);
        notificationStage.setHeight(NOTIFICATION_HEIGHT);

        ImageView m;
        StackPane p = new StackPane();
        switch (azanType) {
            case SOBH:
                m = new ImageView("/image/azan-sobh.jpg");
                break;
            case ZOHRE:
                m = new ImageView("/image/azan-zohre.jpg");
                break;
            case MAGHREB:
                m = new ImageView("/image/azan-maghreb.jpg");
                break;
            default:
                m = new ImageView("/image/azan-mandeh.jpg");
                break;
        }
        m.setFitHeight(NOTIFICATION_HEIGHT);
        m.setFitWidth(NOTIFICATION_WIDTH);
        if (azanType == AzanType.REMINDEING) {
            Label l = new Label(message);
            l.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(40) + "px;-fx-font-family: 'shabnam';-fx-background-color: rgba(255,255,255,0.5)");
            p.getChildren().setAll(m, l);

        } else {
            p.getChildren().setAll(m);
        }

        Scene scene = new Scene(p);
        scene.setOnMouseClicked(event -> {
            notificationStage.close();
        });
        notificationStage.setScene(scene);
        notificationStage.setAlwaysOnTop(true);
        notificationStage.show();

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
