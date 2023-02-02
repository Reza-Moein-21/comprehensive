package ir.comprehensive.jfxapp.component.jfxactivecombo;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;

public class JFXActiveCombo extends JFXComboBox<JFXActiveValue> {


    public JFXActiveCombo() {

        this.getItems().setAll(JFXActiveValue.NONE, JFXActiveValue.ACTIVE, JFXActiveValue.INACTIVE);
        this.setConverter(new StringConverter<JFXActiveValue>() {
            @Override
            public String toString(JFXActiveValue jfxActiveValue) {
                if (jfxActiveValue == null) {
                    return null;
                } else {
                    return jfxActiveValue.getTitle();
                }
            }

            @Override
            public JFXActiveValue fromString(String id) {
                return null;
            }
        });
        this.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case NONE:
                    this.setIsActive(null);
                    return;
                case ACTIVE:
                    this.setIsActive(true);
                    return;
                case INACTIVE:
                    this.setIsActive(false);
                    return;
                default:
            }

        });

    }


    private ObjectProperty<Boolean> isActive = new SimpleObjectProperty<>(this, "isActive", null);

    public final Boolean getIsActive() {
        return isActive.get();
    }

    public final ObjectProperty<Boolean> isActiveProperty() {
        return isActive;
    }

    public final void setIsActive(Boolean isActive) {
        this.isActive.set(isActive);
    }
}
