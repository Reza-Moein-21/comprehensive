package ir.comprehensive.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import ir.comprehensive.utils.MessageUtils;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class YesNoDialog extends JFXDialog {
    Runnable onConfirm;

    {
        JFXButton btnYes = new JFXButton(MessageUtils.Message.YES);
        btnYes.getStyleClass().add("primary-button");
        btnYes.setButtonType(JFXButton.ButtonType.RAISED);
        btnYes.setOnAction(event -> onConfirm.run());
        btnYes.setPrefWidth(300);
        JFXButton btnNo = new JFXButton(MessageUtils.Message.NO);
        btnNo.getStyleClass().add("default-button");
        btnNo.setButtonType(JFXButton.ButtonType.RAISED);
        btnNo.setOnAction(event -> this.close());
        btnNo.setPrefWidth(300);

        HBox hBox = new HBox(20, btnNo, btnYes);

        Label label = new Label(MessageUtils.Message.CONFIRM_DELETE);
        label.setStyle("-fx-font-weight: bold;-fx-font-size: 16");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);

        vBox.setStyle("-fx-spacing: 80;-fx-alignment: CENTER;-fx-padding: 80 110");
        setContent(vBox);

    }

    public Runnable getOnConfirm() {
        return onConfirm;
    }

    public void setOnConfirm(Runnable onConfirm) {
        this.onConfirm = onConfirm;
    }
}