package ir.comprehensive.component;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

import java.util.StringJoiner;


public class Card extends AnchorPane {
    public static final String BODY_STYLE_CLASS = "bodyStyleClass";

    private AnchorPane body;
    private Label bodyTitle;
    //    private Node bodyContent;
    private JFXButton bodyButton;
    private ImageView iconView;
    private ObjectProperty<EventHandler<ActionEvent>> onClick = new SimpleObjectProperty<>(this, "onClick", null);
    private ObjectProperty<Image> icon = new SimpleObjectProperty<>(this, "icon", null);
    private StringProperty title = new SimpleStringProperty(this, "title", "");
    private ObjectProperty<Node> content = new SimpleObjectProperty<>(this, "content");

    public Card() {
        init();
    }

    private void init() {
        /*
         * ********************************
         *   Init bodyTitle
         * ********************************
         */
        bodyTitle = new Label();
        bodyTitle.setAlignment(Pos.CENTER_RIGHT);
        bodyTitle.setTextAlignment(TextAlignment.RIGHT);
        bodyTitle.textProperty().bind(titleProperty());
        bodyTitle.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(40) + "px;-fx-font-family: 'shabnam';");
        AnchorPane.setTopAnchor(bodyTitle, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(bodyTitle, ScreenUtils.getActualSize(10.0));
        AnchorPane.setLeftAnchor(bodyTitle, ScreenUtils.getActualSize(75.0));

        /*
         * ********************************
         *   Init bodyComment
         * ********************************
         */
        StackPane contentWrapper = new StackPane();
        StringJoiner contentWrapperStyle = new StringJoiner(";");
        contentWrapperStyle
                .add("-fx-border-width: " + ScreenUtils.getActualSize(1) + "px 0px 0px 0px")
                .add("-fx-padding: " + ScreenUtils.getActualSize(5) + "px " + ScreenUtils.getActualSize(10) + "px")
                .add("-fx-border-color: #0d47a1;");
        contentWrapper.setStyle(contentWrapperStyle.toString());
        content.addListener((observable, oldValue, newValue) -> {
            contentWrapper.getChildren().setAll(newValue);
        });
        AnchorPane.setTopAnchor(contentWrapper, ScreenUtils.getActualSize(70.0));
        AnchorPane.setRightAnchor(contentWrapper, ScreenUtils.getActualSize(5.0));
        AnchorPane.setBottomAnchor(contentWrapper, ScreenUtils.getActualSize(0.0));
        AnchorPane.setLeftAnchor(contentWrapper, ScreenUtils.getActualSize(5.0));

        /*
         * ********************************
         *   Init bodyButton
         * ********************************
         */
        bodyButton = new JFXButton();
        bodyButton.setStyle("-fx-background-color: transparent;-jfx-disable-visual-focus: true;");
        bodyButton.setText(null);
        bodyButton.onActionProperty().bind(onClickProperty());
        AnchorPane.setTopAnchor(bodyButton, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(bodyButton, ScreenUtils.getActualSize(0.0));
        AnchorPane.setBottomAnchor(bodyButton, ScreenUtils.getActualSize(0.0));
        AnchorPane.setLeftAnchor(bodyButton, ScreenUtils.getActualSize(0.0));

        /*
         * ********************************
         *   Init iconView
         * ********************************
         */
        iconView = new ImageView();
        AnchorPane.setTopAnchor(iconView, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(iconView, ScreenUtils.getActualSize(40.0));
        AnchorPane.setLeftAnchor(iconView, ScreenUtils.getActualSize(10.0));
        iconView.setFitHeight(ScreenUtils.getActualSize(115));
        iconView.setFitWidth(ScreenUtils.getActualSize(115));
        iconView.imageProperty().bind(iconProperty());

        /*
         * ********************************
         *   Init body
         * ********************************
         */
        body = new AnchorPane();
        AnchorPane.setTopAnchor(body, ScreenUtils.getActualSize(50.0));
        AnchorPane.setRightAnchor(body, ScreenUtils.getActualSize(0.0));
        AnchorPane.setBottomAnchor(body, ScreenUtils.getActualSize(0.0));
        AnchorPane.setLeftAnchor(body, ScreenUtils.getActualSize(0.0));
        body.getStyleClass().add(BODY_STYLE_CLASS);
        body.setStyle("-fx-background-radius: "+ScreenUtils.getActualSize(5)+";-fx-border-radius: "+ScreenUtils.getActualSize(5) +";-fx-border-width: "+ScreenUtils.getActualSize(2.5));
        body.getChildren().addAll(bodyTitle, contentWrapper, bodyButton);

        /*
         * ********************************
         *   Init base
         * ********************************
         */
        minHeight(ScreenUtils.getActualSize(150.0));
        minWidth(ScreenUtils.getActualSize(300.0));
        getChildren().addAll(body, iconView);

    }

    public final ObjectProperty<EventHandler<ActionEvent>> onClickProperty() {
        return onClick;
    }

    public final void setOnClick(EventHandler<ActionEvent> value) {
        onClickProperty().set(value);
    }

    public final EventHandler<ActionEvent> getOnClick() {
        return onClickProperty().get();
    }


    public final ObjectProperty<Image> iconProperty() {
        return this.icon;
    }

    public final Image getIcon() {
        return icon == null ? null : icon.get();
    }

    public final void setIcon(Image icon) {
        this.icon.set(icon);
    }


    public final StringProperty titleProperty() {
        return this.title;
    }

    public final String getTitle() {
        return title == null ? "" : title.getValue();
    }

    public final void setTitle(String value) {
        title.setValue(value);
    }


    public Node getContent() {
        return content.get();
    }

    public void setContent(Node content) {
        this.content.set(content);
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }
}
