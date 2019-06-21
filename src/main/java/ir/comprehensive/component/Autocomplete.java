package ir.comprehensive.component;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.model.basemodel.BaseModel;
import ir.comprehensive.utils.MessageUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Autocomplete<T extends BaseModel> extends JFXTextField {
    public static final String CONTENT_STYLE = "content-style";

    private ObjectProperty<T> value = new SimpleObjectProperty<>();
    private Function<String, List<T>> onSearch;
    private List<T> suggestedModels;
    private JFXPopup jfxPopup;
    private boolean isValueChanged;

    public Autocomplete() {


        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValueChanged) {
                isValueChanged = false;
                return;
            }
            if (!this.canTriggerSearch(oldValue, newValue)) {
                return;
            }
            if (newValue.isEmpty()) {
                clearValueAndSuggestedModels();
                closePopup();
                return;
            }

            if (this.onSearch != null) {
                suggestedModels = this.onSearch.apply(newValue);
                openPopup(getPopupContent(suggestedModels));
            } else {
                closePopup();
                this.setText(null);
            }

        });

        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (isNullOrEmpty(suggestedModels)) {
                    this.setText(this.getValue() == null ? "" : this.getValue().getTitle());
                } else {
                    setFirstResultToValue();
                }
                this.closePopup();
            }
        });

        this.setOnKeyPressed(event -> {
            if (KeyCode.ENTER.equals(event.getCode())) {
                if (isNullOrEmpty(suggestedModels)) {
                    this.setText(this.getValue() == null ? "" : this.getValue().getTitle());
                } else {
                    setFirstResultToValue();
                }
                this.closePopup();
            }
        });
    }

    private boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    private void setFirstResultToValue() {
        T firstResult = suggestedModels.get(0);
        this.setValue(firstResult);
        this.setText(firstResult.getTitle());
    }

    private boolean canTriggerSearch(String newValue, String oldValue) {
        return newValue != null && !newValue.equals(oldValue);
    }

    private void openPopup(Region content) {
        closePopup();
        jfxPopup = new JFXPopup(content);

        if (this.getNodeOrientation() == NodeOrientation.LEFT_TO_RIGHT) {
            jfxPopup.show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, this.getLayoutY() + 80);

        } else {
            jfxPopup.show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, this.getLayoutX() - this.getWidth(), this.getLayoutY() + 80);

        }
    }

    private void clearValueAndSuggestedModels() {
        suggestedModels = null;
        this.setValue(null);
    }

    // TODO must refactor later ...
    private VBox getPopupContent(List<T> models) {
        VBox vBox = new VBox();
        // config popup content
        vBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        vBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        vBox.prefWidthProperty().bind(this.widthProperty());
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);


        if (models == null || models.isEmpty()) {
            Label label = new Label(MessageUtils.Message.NOT_FOUND);
            label.prefWidthProperty().bind(this.widthProperty());
            label.getStyleClass().add(CONTENT_STYLE);
            vBox.getChildren().add(label);
            return vBox;
        }
        List<Label> labelList = models.stream().map(model -> {
            Label label = new Label(model.getTitle());
            label.prefWidthProperty().bind(this.widthProperty());
            label.getStyleClass().add(CONTENT_STYLE);
            label.setOnMouseClicked(event -> {
                this.setValue(model);
                this.setText(model.getTitle());
                this.closePopup();
            });
            return label;
        }).collect(Collectors.toList());
        vBox.getChildren().addAll(labelList);
        return vBox;
    }

    private void closePopup() {
        if (this.jfxPopup != null && this.jfxPopup.isShowing()) {
            this.jfxPopup.hide();
        }
        jfxPopup = null;
    }

    public T getValue() {
        return value.get();
    }

    public void setValue(T value) {
        this.value.set(value);
        if (value != null && value.getTitle() != null && !value.getTitle().isEmpty()) {
            this.isValueChanged = true;
            this.setText(value.getTitle());
        } else {
            this.isValueChanged = false;
            this.setText("");
        }
    }

    public ObjectProperty<T> valueProperty() {
        return value;
    }

    public Function<String, List<T>> getOnSearch() {
        return onSearch;
    }

    public void setOnSearch(Function<String, List<T>> onSearch) {
        this.onSearch = onSearch;
    }

}
