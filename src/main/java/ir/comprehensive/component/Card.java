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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;


public class Card extends AnchorPane {
    public static final String BODY_STYLE_CLASS = "bodyStyleClass";

    private AnchorPane body;
    private Label bodyTitle;
    private Label bodyComment;
    private JFXButton bodyButton;
    private ImageView iconView;
    private ObjectProperty<EventHandler<ActionEvent>> onClick = new SimpleObjectProperty<>(this, "onClick", null);
    private ObjectProperty<Image> icon = new SimpleObjectProperty<>(this, "icon", null);
    private StringProperty title = new SimpleStringProperty(this, "title", "");
    private StringProperty comment = new SimpleStringProperty(this, "comment", "");

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
        AnchorPane.setTopAnchor(bodyTitle, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(bodyTitle, ScreenUtils.getActualSize(10.0));
        AnchorPane.setLeftAnchor(bodyTitle, ScreenUtils.getActualSize(75.0));

        /*
         * ********************************
         *   Init bodyComment
         * ********************************
         */
        bodyComment = new Label();
        bodyComment.setAlignment(Pos.TOP_RIGHT);
        bodyComment.setTextAlignment(TextAlignment.RIGHT);
        bodyComment.setWrapText(true);
        bodyComment.setText(comment.get());
        bodyComment.textProperty().bind(commentProperty());
        AnchorPane.setTopAnchor(bodyComment, ScreenUtils.getActualSize(55.0));
        AnchorPane.setRightAnchor(bodyComment, ScreenUtils.getActualSize(5.0));
        AnchorPane.setBottomAnchor(bodyComment, ScreenUtils.getActualSize(0.0));
        AnchorPane.setLeftAnchor(bodyComment, ScreenUtils.getActualSize(5.0));

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
        iconView.setFitHeight(ScreenUtils.getActualSize(96));
        iconView.setFitWidth(ScreenUtils.getActualSize(96));
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
        body.getChildren().addAll(bodyTitle, bodyComment, bodyButton);

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


    public final StringProperty commentProperty() {
        return this.comment;
    }

    public final String getComment() {
        return comment == null ? "" : comment.getValue();
    }

    public final void setComment(String comment) {
        this.comment.set(comment);
    }
}
