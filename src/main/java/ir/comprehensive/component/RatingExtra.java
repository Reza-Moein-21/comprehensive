package ir.comprehensive.component;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

public class RatingExtra extends HBox {
    private IntegerProperty max = new SimpleIntegerProperty(this, "max", 5); //$NON-NLS-1$

    private DoubleProperty rating = new SimpleDoubleProperty(this, "rating", 0); //$NON-NLS-1$

    private StringProperty title = new SimpleStringProperty(this, "title");

    private BooleanProperty readonly = new SimpleBooleanProperty(this, "readonly", false);

    public RatingExtra() {
        Rating ratingComponent = new Rating();
        ratingComponent.maxProperty().bindBidirectional(max);
        ratingComponent.ratingProperty().bindBidirectional(rating);
        ratingComponent.setMaxHeight(1);


        JFXButton btnClear = new JFXButton();
        readonly.addListener((observable, oldValue, newValue) -> {
            ratingComponent.ratingProperty().unbind();

            if (newValue) {
                btnClear.setVisible(false);
                ratingComponent.ratingProperty().bind(rating);
            } else {
                btnClear.setVisible(true);
                ratingComponent.ratingProperty().bindBidirectional(rating);

            }
        });
        btnClear.setStyle("-jfx-disable-visual-focus: true;-fx-background-radius: 10; -fx-background-size: contain; -fx-background-repeat: no-repeat;-fx-background-position: center;-fx-background-image: url('/image/clear.png')");
        btnClear.setPrefWidth(ScreenUtils.getActualSize(5));
        btnClear.setPrefHeight(ScreenUtils.getActualSize(5));
        btnClear.setOnAction((e) -> ratingComponent.setRating(0.0D));
        Label lblTile = new Label();
        lblTile.textProperty().bind(title);

        this.setSpacing(ScreenUtils.getActualSize(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(lblTile, ratingComponent, btnClear);
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
