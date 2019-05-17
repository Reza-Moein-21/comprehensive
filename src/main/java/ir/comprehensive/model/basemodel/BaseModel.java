package ir.comprehensive.model.basemodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.Button;

import static ir.comprehensive.utils.MessageUtils.getMessage;


public abstract class BaseModel {
    protected LongProperty id = new SimpleLongProperty();
    private Button btnEdit = new Button(getMessage("edit"));
    private Editable onEdit;

    {
        btnEdit.setOnAction(event -> onEdit.edit(id));
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
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
