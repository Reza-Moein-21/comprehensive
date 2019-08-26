package ir.comprehensive.component;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class JavaFxComponent extends StackPane {

    protected abstract Node render();

    public void refresh() {
        this.getChildren().setAll(render());
    }
}
