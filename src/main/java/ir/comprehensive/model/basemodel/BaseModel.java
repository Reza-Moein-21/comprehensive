package ir.comprehensive.model.basemodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;

import static ir.comprehensive.utils.MessageUtils.getMessage;


public abstract class BaseModel {
    protected ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private Button btnEdit = new Button(getMessage("edit"));
    private Editable onEdit;

    {
        btnEdit.setOnAction(event -> {
            if (onEdit != null) {
                onEdit.edit(id);
            }
        });
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Editable getOnEdit() {
        return onEdit;
    }

    public void setOnEdit(Editable onEdit) {
        this.onEdit = onEdit;
    }
}
