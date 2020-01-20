package ir.comprehensive.component.hadis;

import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Hadis extends VBox implements Initializable {

    @FXML
    ImageView imgRight;
    @FXML
    ImageView imgLeft;
    @FXML
    ImageView imgHeader;
    @FXML
    Label lblHeader;
    @FXML
    Label lblTitle;
    @FXML
    Label lblContent;
    private StringProperty title = new SimpleStringProperty();
    private StringProperty content = new SimpleStringProperty();

    public Hadis() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/hadis.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setMinWidth(ScreenUtils.getActualSize(1000));
        imgRight.setFitHeight(ScreenUtils.getActualSize(340));
        imgRight.setFitWidth(ScreenUtils.getActualSize(200));
        imgLeft.setFitHeight(ScreenUtils.getActualSize(340));
        imgLeft.setFitWidth(ScreenUtils.getActualSize(200));
        imgHeader.setFitHeight(ScreenUtils.getActualSize(250));
        imgHeader.setFitWidth(ScreenUtils.getActualSize(430));
        lblHeader.setStyle("-fx-text-fill: #ffffff;-fx-padding: 0 0 " + ScreenUtils.getActualSize(18) + " 0");

        lblTitle.textProperty().bind(titleProperty());
        lblContent.textProperty().bind(contentProperty());
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }
}
