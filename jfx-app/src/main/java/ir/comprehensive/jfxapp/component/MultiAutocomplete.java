package ir.comprehensive.jfxapp.component;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.fxmodel.WarehouseTagFxModel;
import ir.comprehensive.fxmodel.basemodel.BaseFxModel;
import ir.comprehensive.jfxapp.utils.MessageUtils;
import ir.comprehensive.jfxapp.utils.ScreenUtils;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultiAutocomplete<T extends BaseFxModel> extends VBox {
    public static final String CONTENT_STYLE = "content-style";
    private static final Double TOP_PADDING = ScreenUtils.getActualSize(80);

    private ListProperty<T> valueList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private Function<String, List<T>> onSearch;
    private TilePane componentList = new TilePane();

    private List<T> suggestedModels;
    private JFXTextField textField = new JFXTextField();
    private BooleanProperty labelFloat = new SimpleBooleanProperty();
    private JFXPopup jfxPopup;
    private StringProperty promptText = new SimpleStringProperty(this, "promptText", "") {
        @Override
        protected void invalidated() {
            // Strip out newlines
            String txt = get();
            if (txt != null && txt.contains("\n")) {
                txt = txt.replace("\n", "");
                set(txt);
            }
        }
    };

    public MultiAutocomplete() {
        componentList.setHgap(ScreenUtils.getActualSize(5));
        componentList.setVgap(ScreenUtils.getActualSize(5));

        valueList.addListener((observable, oldValue, newValue) -> {
            componentList.getChildren().setAll(this.valueList.get().stream().map(this::convertToComponent).collect(Collectors.toList()));
        });
        textField.labelFloatProperty().bind(this.labelFloat);
        textField.promptTextProperty().bind(this.promptText);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
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
                VBox popupContent = getPopupContent(suggestedModels);
                applyFontStyle(popupContent);
                openPopup(popupContent);
            } else {
                closePopup();
                textField.setText(null);
            }

        });

        this.textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            this.textField.setText("");
        });

        this.setOnKeyPressed(event -> {
            if (KeyCode.ENTER.equals(event.getCode())) {
                // TODO must fix this shite. Must use  Option model.
                WarehouseTagFxModel model = new WarehouseTagFxModel();
                model.setId(-new Date().getTime());
                model.setTitle(this.textField.getText() != null ? this.textField.getText().trim() : this.textField.getText());
                setToValueListIfNotExist((T) model);
                this.textField.setText("");
                this.closePopup();
            }
        });

        this.setSpacing(ScreenUtils.getActualSize(10));
        this.getChildren().addAll(textField, componentList);
    }

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }

    private void setToValueListIfNotExist(T model) {
        if (this.valueList.stream().noneMatch(m -> m.getTitle() != null && model.getTitle() != null && m.getTitle().equals(model.getTitle()))) {
            this.valueList.add(model);
        }
    }

    private HBox convertToComponent(T model) {
        HBox hBox = new HBox();
        StringJoiner style = new StringJoiner(";");
        style.add("-fx-background-color: #CFD8DC").add("-fx-spacing: " + ScreenUtils.getActualSize(10))
                .add("-fx-background-radius: " + ScreenUtils.getActualSize(20)).add("-fx-alignment: center").add("-fx-padding: " + ScreenUtils.getActualSize(3) + " " + ScreenUtils.getActualSize(10));
        hBox.setStyle(style.toString());

        Circle circle = new Circle(ScreenUtils.getActualSize(20), Color.valueOf("#EFEBE9"));
        circle.setId(String.valueOf(model.getId()));
        StackPane stackPane = new StackPane(circle, new Text("x"));
        stackPane.setOnMouseEntered(event -> circle.setFill(Color.valueOf("#F5F5F5")));
        stackPane.setOnMouseExited(event -> circle.setFill(Color.valueOf("#EFEBE9")));
        stackPane.setOnMouseClicked(event -> this.valueList.remove(model));

        Label lblItemTitle = new Label(model.getTitle());
        hBox.getChildren().addAll(stackPane, lblItemTitle);
        return hBox;
    }

    private boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    private void setFirstResultToValue() {
        T firstResult = suggestedModels.get(0);
        this.setToValueListIfNotExist(firstResult);
        textField.setText("");
    }

    private boolean canTriggerSearch(String newValue, String oldValue) {
        return newValue != null && !newValue.equals(oldValue);
    }

    private void openPopup(Region content) {
        closePopup();
        jfxPopup = new JFXPopup(content);

        if (this.getNodeOrientation() == NodeOrientation.LEFT_TO_RIGHT) {
            jfxPopup.show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, TOP_PADDING);

        } else {
            jfxPopup.show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -this.getWidth(), TOP_PADDING);

        }
    }

    private void clearValueAndSuggestedModels() {
        suggestedModels = null;
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
                this.setToValueListIfNotExist(model);
                textField.setText("");
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

    public ObservableList<T> getValueList() {
        return valueList.get();
    }

    public void setValueList(ObservableList<T> valueList) {
        if (valueList == null) {
            valueList = FXCollections.observableArrayList();
        }
        this.valueList.set(valueList);
    }

    public ListProperty<T> valueListProperty() {
        return valueList;
    }

    public Function<String, List<T>> getOnSearch() {
        return onSearch;
    }

    public void setOnSearch(Function<String, List<T>> onSearch) {
        this.onSearch = onSearch;
    }

    public final StringProperty promptTextProperty() {
        return promptText;
    }

    public final String getPromptText() {
        return promptText.get();
    }

    public final void setPromptText(String value) {
        promptText.set(value);
    }

    public boolean isLabelFloat() {
        return labelFloat.get();
    }

    public void setLabelFloat(boolean labelFloat) {
        this.labelFloat.set(labelFloat);
    }

    public BooleanProperty labelFloatProperty() {
        return labelFloat;
    }

}
