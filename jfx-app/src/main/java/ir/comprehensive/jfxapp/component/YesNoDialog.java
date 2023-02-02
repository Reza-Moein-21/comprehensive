package ir.comprehensive.jfxapp.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import ir.comprehensive.jfxapp.utils.MessageUtils;
import ir.comprehensive.jfxapp.utils.ScreenUtils;
import javafx.geometry.Insets;
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
        btnYes.setPrefWidth(ScreenUtils.getActualSize(300));
        JFXButton btnNo = new JFXButton(MessageUtils.Message.NO);
        btnNo.getStyleClass().add("default-button");
        btnNo.setButtonType(JFXButton.ButtonType.RAISED);
        btnNo.setOnAction(event -> this.close());
        btnNo.setPrefWidth(ScreenUtils.getActualSize(300));

        HBox hBox = new HBox(ScreenUtils.getActualSize(20), btnNo, btnYes);

        Label label = new Label(MessageUtils.Message.CONFIRM_DELETE);
        label.setStyle("-fx-font-weight: bold;");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);

        vBox.setStyle("-fx-alignment: CENTER;");
        vBox.setSpacing(ScreenUtils.getActualSize(80));
        vBox.setPadding(new Insets(ScreenUtils.getActualSize(80), ScreenUtils.getActualSize(110), ScreenUtils.getActualSize(80), ScreenUtils.getActualSize(110)));
        setContent(vBox);

    }

    public Runnable getOnConfirm() {
        return onConfirm;
    }

    public void setOnConfirm(Runnable onConfirm) {
        this.onConfirm = onConfirm;
    }
}
