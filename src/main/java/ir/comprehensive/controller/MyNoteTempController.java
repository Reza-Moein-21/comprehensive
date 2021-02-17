package ir.comprehensive.controller;

import ir.comprehensive.component.RatingExtra;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.CustomTableColumn;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.fxmapper.MyNoteTempFxMapper;
import ir.comprehensive.fxmodel.MyNoteTempFxModel;
import ir.comprehensive.service.MyNoteTempFxService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

@Controller
public class MyNoteTempController implements Initializable {
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public DataTable<MyNoteTempFxModel> tblMyNoteTemp;
    @FXML
    public CustomTableColumn<MyNoteTempFxModel, RatingExtra> colPriority;
    @FXML
    public VBox parent;
    public CustomTableColumn<MyNoteTempFxModel, String> colDescription;

    @Autowired
    StartController startController;
    @Autowired
    private MyNoteTempFxService myNoteTempService;
    @Autowired
    private MyNoteTempFxMapper mapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dlgDelete.setDialogContainer(startController.mainStack);
        applyFontStyle(dlgDelete);

        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));

        colPriority.setCellValueFactory(param -> new ReadOnlyObjectWrapper<RatingExtra>(new RatingExtra(null, param.getValue().getPriority(), null, true)));
        colPriority.setComparator(Comparator.comparing(RatingExtra::getRating));

        colDescription.setCellValueFactory(param -> new ReadOnlyObjectWrapper<String>(getRightDescription(param)));

        tblMyNoteTemp.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    myNoteTempService.deleteAll(selectedIds);
                    Notify.showSuccessMessage(MessageUtils.Message.REMOVE_FROM_MY_NOTE_TEMP);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });
        tblMyNoteTemp.setItemPage(pageRequest -> myNoteTempService.loadItem(pageRequest));
        updateDataTable();
    }

    private String getRightDescription(TableColumn.CellDataFeatures<MyNoteTempFxModel, String> param) {
        final int MAX_LENGTH = 50;
        String description = param.getValue().getDescription();
        if (description == null) {
            return "";
        }
        int originalLength = description.length();

        description = description.replace("\n", " ");
        description = description.substring(0, Math.min(description.length(), MAX_LENGTH));
        return originalLength > MAX_LENGTH ? description + "..." : description;
    }

    public void updateDataTable() {
        tblMyNoteTemp.refresh();
    }

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }


}
