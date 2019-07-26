package ir.comprehensive.component;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class RatingExtra extends HBox {
    private static final int STAR_SIZE = 28;

    private IntegerProperty max = new SimpleIntegerProperty(this, "max", 5); //$NON-NLS-1$

    private DoubleProperty rating = new SimpleDoubleProperty(this, "rating", 0); //$NON-NLS-1$

    private StringProperty title = new SimpleStringProperty(this, "title");

    private BooleanProperty readonly = new SimpleBooleanProperty(this, "readonly", false);


    private List<JFXButton> stars = new ArrayList<>();

    public RatingExtra() {
        rating.addListener((observable, oldValue, newValue) -> this.changeButtonStarts(newValue.intValue()));

        for (int i = 1; i <= 5; i++) {
            JFXButton btnStar = new JFXButton();
            btnStar.setPadding(new Insets(ScreenUtils.getActualSize(3)));
            setDisableStar(btnStar);
            btnStar.setRipplerFill(Paint.valueOf("#757575"));
            btnStar.setId(String.valueOf(i));
            btnStar.setOnAction(event -> {
                if (this.readonly.getValue()) {
                    return;
                }
                JFXButton source = (JFXButton) event.getSource();
                this.rating.setValue(Double.valueOf(source.getId()));
                changeButtonStarts(Integer.valueOf(source.getId()));
            });
            stars.add(btnStar);
        }


        JFXButton btnClear = new JFXButton();
        btnClear.setStyle("-jfx-disable-visual-focus: true;-fx-background-radius: 10; -fx-background-size: contain; -fx-background-repeat: no-repeat;-fx-background-position: center;-fx-background-image: url('/image/clear.png')");
        btnClear.visibleProperty().bind(Bindings.not(readonly));
        btnClear.setRipplerFill(Paint.valueOf("#757575"));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/image/clear.png").toExternalForm()));
        imageView.setFitHeight(ScreenUtils.getActualSize(STAR_SIZE));
        imageView.setFitWidth(ScreenUtils.getActualSize(STAR_SIZE));
        btnClear.setGraphic(imageView);
        btnClear.setPadding(new Insets(ScreenUtils.getActualSize(3)));
        btnClear.setOnAction((e) -> this.rating.setValue(0.0D));


        Label lblTile = new Label();
        lblTile.textProperty().bind(title);

        this.setSpacing(ScreenUtils.getActualSize(10));
        this.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(this.stars);
        hBox.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(lblTile, hBox, btnClear);
    }

    private void changeButtonStarts(int id) {
        stars.forEach(btn -> {
            if (Integer.valueOf(btn.getId()) <= id) {
                this.setEnableStar(btn);
            } else {
                this.setDisableStar(btn);
            }
        });
    }

    private void setDisableStar(JFXButton btnStar) {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/image/unselected-star.png").toExternalForm()));
        imageView.setFitHeight(ScreenUtils.getActualSize(STAR_SIZE));
        imageView.setFitWidth(ScreenUtils.getActualSize(STAR_SIZE));
        btnStar.setGraphic(imageView);
    }

    private void setEnableStar(JFXButton btnStar) {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/image/selected-star.png").toExternalForm()));
        imageView.setFitHeight(ScreenUtils.getActualSize(STAR_SIZE));
        imageView.setFitWidth(ScreenUtils.getActualSize(STAR_SIZE));
        btnStar.setGraphic(imageView);
    }

    public RatingExtra(Integer max, Double rating, String title, Boolean readonly) {
        this();
        this.max.setValue(max == null ? 5 : max);
        this.rating.setValue(rating);
        this.readonly.setValue(readonly);
        this.title.setValue(title);

    }

    public final String getTitle() {
        return title.get();
    }

    public final StringProperty titleProperty() {
        return title;
    }

    public final void setTitle(String title) {
        this.title.set(title);
    }

    public final DoubleProperty ratingProperty() {
        return rating;
    }

    public final void setRating(double value) {
        ratingProperty().set(value);
    }

    public final double getRating() {
        return rating == null ? 3 : rating.get();
    }

    public final IntegerProperty maxProperty() {
        return max;
    }

    public final void setMax(int value) {
        maxProperty().set(value);
    }

    public final int getMax() {
        return max == null ? 5 : max.get();
    }

    public final boolean isReadonly() {
        return readonly.get();
    }

    public final BooleanProperty readonlyProperty() {
        return readonly;
    }

    public final void setReadonly(boolean readonly) {
        this.readonly.set(readonly);
    }
}
