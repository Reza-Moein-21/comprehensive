package ir.comprehensive.component.basetable;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableColumn;

public class CustomTableColumn<S, T> extends TableColumn<S, T> {
    public CustomTableColumn() {
    }

    public CustomTableColumn(String text) {
        super(text);
    }

    private final DoubleProperty percentageWidth = new SimpleDoubleProperty(100);
    private final DoubleProperty percentageMinWidth = new SimpleDoubleProperty();

    {
        tableViewProperty().addListener((ov, t, t1) -> {
            if (CustomTableColumn.this.prefWidthProperty().isBound()) {
                CustomTableColumn.this.prefWidthProperty().unbind();
            }

            CustomTableColumn.this.prefWidthProperty().bind(t1.widthProperty().multiply(Bindings.divide(percentageWidth, 100)));
            CustomTableColumn.this.minWidthProperty().bind(t1.widthProperty().multiply(Bindings.divide(percentageMinWidth, 100)));
        });
    }

    public final DoubleProperty percentageWidthProperty() {
        return this.percentageWidth;
    }

    public final double getPercentageWidth() {
        return this.percentageWidthProperty().get();
    }

    public final void setPercentageWidth(double value) throws IllegalArgumentException {
        if (value >= 0 && value <= 100) {
            this.percentageWidthProperty().set(value);
        } else {
            throw new IllegalArgumentException(String.format("The provided percentage width is not between 0.0 and 100 Value is: %1$s", value));
        }
    }

    public final double getPercentageMinWidth() {
        return percentageMinWidth.get();
    }

    public final DoubleProperty percentageMinWidthProperty() {
        return percentageMinWidth;
    }

    public final void setPercentageMinWidth(double percentageMinWidth) {
        if (percentageMinWidth >= 0 && percentageMinWidth <= 100) {
            this.percentageMinWidth.set(percentageMinWidth);
        } else {
            throw new IllegalArgumentException(String.format("The provided percentage min width is not between 0.0 and 100 Value is: %1$s", percentageMinWidth));
        }
    }
}