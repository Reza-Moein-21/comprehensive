package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class MyNoteTempModel extends BaseModel {

    private ObjectProperty<LocalDateTime> creationTime = new SimpleObjectProperty<>();

    private ObjectProperty<MyNoteModel> myNote = new SimpleObjectProperty<>();
}
