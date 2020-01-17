package ir.comprehensive.controller;

import ir.comprehensive.component.RatingExtra;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.CustomTableColumn;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.mapper.MyNoteTempMapper;
import ir.comprehensive.model.MyNoteTempModel;
import ir.comprehensive.service.MyNoteTempService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class MyNoteTempController implements Initializable {
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public DataTable<MyNoteTempModel> tblMyNoteTemp;
    @FXML
    public CustomTableColumn<MyNoteTempModel, RatingExtra> colPriority;
    @FXML
    public VBox parent;

    @Autowired
    StartController startController;
    @Autowired
    private MyNoteTempService myNoteTempService;
    @Autowired
    private MyNoteTempMapper mapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dlgDelete.setDialogContainer(startController.mainStack);
        applyFontStyle(dlgDelete);

        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));

        colPriority.setCellValueFactory(param -> new ReadOnlyObjectWrapper<RatingExtra>(new RatingExtra(null, param.getValue().getPriority(), null, true)));
        colPriority.setComparator(Comparator.comparing(RatingExtra::getRating));

        tblMyNoteTemp.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    myNoteTempService.deleteAll(selectedIds);
                    Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE_TEMP + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        updateDataTable();
    }

    public void updateDataTable() {
        tblMyNoteTemp.setItems(myNoteTempService.loadAll()
                .map(myNoteTemps -> myNoteTemps.stream().map(mapper::entityToModel).collect(Collectors.toList()))
                .map(FXCollections::observableArrayList).get());
    }

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }


}
