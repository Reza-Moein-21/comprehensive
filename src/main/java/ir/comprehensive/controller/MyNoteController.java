package ir.comprehensive.controller;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.RatingExtra;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.mapper.MyNoteMapper;
import ir.comprehensive.model.MyNoteModel;
import ir.comprehensive.service.MyNoteService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class MyNoteController implements Initializable {
    @Autowired
    StartController startController;
    @Autowired
    MyNoteService myNoteService;
    @Autowired
    MyNoteMapper mapper;

    @FXML
    MyNoteModel createModel;
    @FXML
    MyNoteModel searchModel;
    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public SimpleDatePicker sdpCreationDateC;
    @FXML
    public JFXTextArea txaDescriptionC;
    @FXML
    public RatingExtra rtnPriorityS;
    @FXML
    public RatingExtra rtnPriorityC;
    @FXML
    public Label lblTitleShow;
    @FXML
    public JFXTextArea txaDescriptionShow;
    @FXML
    public SimpleDatePicker sdpCreationDateFromS;
    @FXML
    public SimpleDatePicker sdpCreationDateToS;
    @FXML
    public JFXTextField txfTitleS;
    @FXML
    public CheckBox chbIsActiveS;
    @FXML
    public CheckBox chbIsActiveC;

    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public VBox parent;
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public JFXDialog dlgShowDescription;
    @FXML
    public VBox vbxCreateContent;
    @FXML
    public VBox vbxShowDescriptionContent;
    @FXML
    public VBox vbxShowDescriptionCenter;
    @FXML
    public HBox hbxCreateCheckButton;
    @FXML
    public HBox hbxCreateHeader;
    @FXML
    public HBox hbxShowDescriptionHeader;
    @FXML
    public HBox hbxSearchButtonPanel;
    @FXML
    public GridPane grdSearchFooter;
    @FXML
    public JFXButton btnCreate;
    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;
    @FXML
    public GridPane grdSearch;
    @FXML
    public GridPane grdCreateFooter;
    @FXML
    public GridPane grdSearchFromTo;
    @FXML
    public GridPane grdCreateCenter;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public HBox hbxShowDescriptionFooter;
    @FXML
    public DataTable<MyNoteModel> tblMyNote;
    @FXML
    public TableColumn<MyNoteModel, String> colTitle;
    @FXML
    public TableColumn<MyNoteModel, String> colDescription;
    @FXML
    public TableColumn<MyNoteModel, PersianDate> colCreateDate;
    @FXML
    public TableColumn<MyNoteModel, RatingExtra> colPriority;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        dlgShowDescription.setDialogContainer(startController.mainStack);

        //
        sdpCreationDateC.setDialogContainer(startController.mainStack);
        sdpCreationDateFromS.setDialogContainer(startController.mainStack);
        sdpCreationDateToS.setDialogContainer(startController.mainStack);

        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));


        tblMyNote.setOnEdit(selectedItem -> {
            MyNoteModel editModel = myNoteService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            txfTitleC.setText(editModel.getTitle());
            sdpCreationDateC.setValue(editModel.getCreationDate());
            txaDescriptionC.setText(editModel.getDescription());
            chbIsActiveC.setVisible(true);
            chbIsActiveC.setSelected(editModel.isIsActive());
            rtnPriorityC.setRating(editModel.getPriority());
            dlgCreate.show();

        });

        tblMyNote.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    myNoteService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable(true);
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });
        tblMyNote.setOnVisit(selectedItem -> {
            myNoteService.load(selectedItem.getId()).map(mapper::entityToModel).ifPresent(loadedModel -> {
                lblTitleShow.setText(loadedModel.getTitle());
                txaDescriptionShow.setEditable(false);
                txaDescriptionShow.setText(loadedModel.getDescription());
                dlgShowDescription.show();
            });
        });
        updateDataTable(true);

        colTitle.setMinWidth(ScreenUtils.getActualSize(650));
        colTitle.setPrefWidth(ScreenUtils.getActualSize(700));
        colTitle.setSortable(false);

        colDescription.setCellValueFactory(param -> new ReadOnlyObjectWrapper<String>(getRightDescription(param)));
        colDescription.setPrefWidth(ScreenUtils.getActualSize(700));
        colDescription.setSortable(false);

        colPriority.setCellValueFactory(param -> new ReadOnlyObjectWrapper<RatingExtra>(new RatingExtra(null, param.getValue().getPriority(), null, true)));
        colPriority.setMinWidth(ScreenUtils.getActualSize(210));
        colPriority.setPrefWidth(ScreenUtils.getActualSize(210));
        colPriority.setResizable(false);
        colPriority.setComparator(Comparator.comparing(RatingExtra::getRating));

        colCreateDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getCreationDate() == null ? null : PersianDate.fromGregorian(param.getValue().getCreationDate())));
        colCreateDate.setMinWidth(ScreenUtils.getActualSize(210));
        colCreateDate.setPrefWidth(ScreenUtils.getActualSize(210));
        colCreateDate.setResizable(false);


        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));
        sdpCreationDateC.setValidators(Collections.singletonList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.CREATION_DATE)));

        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1600));

        vbxShowDescriptionContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxShowDescriptionContent.setPrefWidth(ScreenUtils.getActualSize(1600));

        vbxShowDescriptionCenter.setPadding(new Insets(ScreenUtils.getActualSize(50)));
        vbxShowDescriptionCenter.setSpacing(ScreenUtils.getActualSize(50));

        hbxCreateHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        hbxCreateCheckButton.setSpacing(ScreenUtils.getActualSize(20));
        txaDescriptionC.setMaxHeight(ScreenUtils.getActualSize(400));

        hbxShowDescriptionHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        hbxShowDescriptionFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxShowDescriptionFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        grdSearch.setPrefHeight(ScreenUtils.getActualSize(300));
        grdSearch.setHgap(ScreenUtils.getActualSize(20));
        grdSearch.setVgap(ScreenUtils.getActualSize(50));
        grdSearch.setPadding(new Insets(ScreenUtils.getActualSize(42), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(25)));

        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));

        grdCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(25)));

        grdSearchFromTo.setHgap(ScreenUtils.getActualSize(10));


        grdCreateCenter.setHgap(ScreenUtils.getActualSize(20));
        grdCreateCenter.setVgap(ScreenUtils.getActualSize(50));
        grdCreateCenter.setPadding(new Insets(ScreenUtils.getActualSize(50)));

        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));


        btnSearch.setPrefWidth(ScreenUtils.getActualSize(400));
        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        btnShowAll.setPrefWidth(ScreenUtils.getActualSize(400));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

    }

    private String getRightDescription(TableColumn.CellDataFeatures<MyNoteModel, String> param) {
        final int MAX_LENGTH = 30;
        String description = param.getValue().getDescription();
        if (description == null) {
            return "";
        }
        int originalLength = description.length();

        description = description.replace(System.getProperty("line.separator"), " ");
        description = description.substring(0, Math.min(description.length(), MAX_LENGTH));
        return originalLength > MAX_LENGTH ? description + "..." : description;
    }

    private void updateDataTable(boolean allActive) {
        if (allActive)
            tblMyNote.setItems(myNoteService.loadAllActive()
                    .map(myNotes -> myNotes.stream().map(mapper::entityToModel).collect(Collectors.toList()))
                    .map(FXCollections::observableArrayList).get());
        else
            tblMyNote.setItems(myNoteService.loadAll()
                    .map(myNotes -> myNotes.stream().map(mapper::entityToModel).collect(Collectors.toList()))
                    .map(FXCollections::observableArrayList).get());
    }

    @FXML
    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    @FXML
    public void showCreateDialog(ActionEvent actionEvent) {
        createModel.setId(null);
        txfTitleC.setText("");
        txaDescriptionC.setText("");
        sdpCreationDateC.setValue(LocalDate.now());
        chbIsActiveC.setVisible(false);
        rtnPriorityC.setRating(0);
        dlgCreate.show();
    }

    private boolean validateBeforeSave() {
        boolean titleValidate = txfTitleC.validate();
        boolean creationDateValidate = sdpCreationDateC.validate();

        return titleValidate && creationDateValidate;
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        if (validateBeforeSave()) {
            try {
                myNoteService.saveOrUpdate(mapper.modelToEntity(createModel));
                dlgCreate.close();
                updateDataTable(true);
                Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE + " " + MessageUtils.Message.SUCCESS_SAVE);
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }
        }
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblMyNote.setItems(myNoteService.search(searchModel).map(categories -> categories.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());

    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfTitleS.setText(null);
        sdpCreationDateFromS.setValue(null);
        sdpCreationDateToS.setValue(null);
        chbIsActiveS.setSelected(true);
        rtnPriorityS.setRating(0.0);
        updateDataTable(false);
    }

    @FXML
    public void closeShowDescriptionDialog(ActionEvent actionEvent) {
        dlgShowDescription.close();
    }
}
