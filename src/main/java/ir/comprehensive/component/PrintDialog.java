package ir.comprehensive.component;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ReportUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

public class PrintDialog extends JFXDialog {

    Consumer<PrintModel> onPrintConfirm;
    private static final String DEFAULT_FILE_NAME = PersianDate.now().toString() + "_Print_"+ new Date().getTime();
    PrintModel printModel = new PrintModel();

    {
        JFXButton btnPrint = new JFXButton(MessageUtils.Message.PRINT);
        btnPrint.getStyleClass().add("primary-button");
        btnPrint.setButtonType(JFXButton.ButtonType.RAISED);
        btnPrint.setOnAction(event -> onPrintConfirm.accept(printModel));
        btnPrint.setPrefWidth(ScreenUtils.getActualSize(300));


        JFXButton btnCancel = new JFXButton(MessageUtils.Message.CANCEL);
        btnCancel.getStyleClass().add("default-button");
        btnCancel.setButtonType(JFXButton.ButtonType.RAISED);
        btnCancel.setOnAction(event -> this.close());
        btnCancel.setPrefWidth(ScreenUtils.getActualSize(300));

        HBox buttonHBox = new HBox(ScreenUtils.getActualSize(20), btnCancel, btnPrint);
        buttonHBox.setAlignment(Pos.CENTER);


        VBox formPanel = getFormPanel();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(formPanel, buttonHBox);

        vBox.setStyle("-fx-alignment: CENTER_RIGHT;");
        vBox.setSpacing(ScreenUtils.getActualSize(90));
        vBox.setPadding(new Insets(ScreenUtils.getActualSize(50)));
        vBox.setPrefWidth(ScreenUtils.getActualSize(1024));
        vBox.setPrefHeight(ScreenUtils.getActualSize(500));
        setTransitionType(DialogTransition.TOP);
        setOverlayClose(false);
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        setContent(vBox);

    }

    public Consumer<PrintModel> getOnPrintConfirm() {
        return onPrintConfirm;
    }

    public void setOnPrintConfirm(Consumer<PrintModel> onPrintConfirm) {
        this.onPrintConfirm = onPrintConfirm;
    }

    private VBox getFormPanel() {
        JFXComboBox<ReportUtils.PrintType> cmbType = new JFXComboBox<>();
        cmbType.setPromptText(MessageUtils.Message.PRINT_TYPE);
        cmbType.setLabelFloat(true);
        cmbType.setItems(FXCollections.observableArrayList(ReportUtils.PrintType.values()));
        cmbType.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.printModel.setType(newValue);
        });
        cmbType.setValue(ReportUtils.PrintType.PDF);


        JFXTextField txtTitle = new JFXTextField();
        txtTitle.setLabelFloat(true);
        txtTitle.setPromptText(MessageUtils.Message.PRINT_TITLE);
        txtTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            this.printModel.setTitle(newValue);
        });
        HBox.setHgrow(txtTitle, Priority.ALWAYS);

        HBox hBox = new HBox(ScreenUtils.getActualSize(10), cmbType, txtTitle);

        Label lblFileName = new Label();
        JFXButton btnOpenFile = new JFXButton(MessageUtils.Message.PRINT_PATH);
        btnOpenFile.getStyleClass().add("default-button");
        btnOpenFile.setButtonType(JFXButton.ButtonType.RAISED);
        btnOpenFile.setOnAction(event -> {
            Window window = this.getScene().getWindow();
            File file = new FileChooser().showSaveDialog(window);
            if (Objects.isNull(file)) {
                return;
            }
            lblFileName.setText(file.getName());
            this.printModel.setDestinationPath(file.getPath());
        });
        HBox.setHgrow(lblFileName, Priority.ALWAYS);
        HBox hBox1 = new HBox(ScreenUtils.getActualSize(20), btnOpenFile, lblFileName);

        return new VBox(ScreenUtils.getActualSize(50), hBox, hBox1);
    }

    public static class PrintModel {
        private ReportUtils.PrintType type;
        private String title;
        private String destinationPath;

        public ReportUtils.PrintType getType() {
            return type;
        }

        public void setType(ReportUtils.PrintType type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDestinationPath() {
            if (destinationPath == null) {
                return System.getProperty("user.home") + "/Desktop/" + DEFAULT_FILE_NAME;
            }
            return destinationPath;
        }

        public void setDestinationPath(String destinationPath) {
            this.destinationPath = destinationPath;
        }

    }
}

