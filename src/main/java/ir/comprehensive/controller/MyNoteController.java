package ir.comprehensive.controller;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.RatingExtra;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.CustomTableColumn;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.component.calenderwidget.CalenderEvent;
import ir.comprehensive.component.calenderwidget.CalenderWidget;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.component.jfxactivecombo.JFXActiveCombo;
import ir.comprehensive.component.jfxactivecombo.JFXActiveValue;
import ir.comprehensive.fxmapper.MyNoteFxMapper;
import ir.comprehensive.fxmapper.PersonFxMapper;
import ir.comprehensive.fxmodel.MyNoteCategoryFxModel;
import ir.comprehensive.fxmodel.MyNoteFxModel;
import ir.comprehensive.fxmodel.PersonFxModel;
import ir.comprehensive.service.MyNoteFxService;
import ir.comprehensive.service.MyNoteTempFxService;
import ir.comprehensive.service.PersonFxService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Controller
public class MyNoteController implements Initializable {
    @Autowired
    StartController startController;
    @Autowired
    MyNoteFxService myNoteService;
    @Autowired
    MyNoteTempFxService myNoteTempService;
    @Autowired
    private PersonFxService personService;
    @Autowired
    PersonFxMapper personMapper;
    @Autowired
    MyNoteFxMapper mapper;


    @FXML
    public Autocomplete<PersonFxModel> autPersonS;
    @FXML
    public Autocomplete<PersonFxModel> autPersonC;
    @FXML
    public JFXActiveCombo cmbIsActiveS;
    @FXML
    public JFXTextField txfDescriptionS;
    @FXML
    MyNoteFxModel createModel;
    @FXML
    MyNoteFxModel searchModel;
    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public CalenderWidget calNoteSearch;
    @FXML
    public TabPane tbpDateSearch;
    @FXML
    public SimpleDatePicker sdpCreationDateC;
    @FXML
    public TextArea txaDescriptionC;
    @FXML
    public RatingExtra rtnPriorityS;
    @FXML
    public RatingExtra rtnPriorityC;
    @FXML
    public Label lblTitleShow;
    @FXML
    public TextArea txaDescriptionShow;
    @FXML
    public SimpleDatePicker sdpCreationDateFromS;
    @FXML
    public SimpleDatePicker sdpCreationDateToS;
    @FXML
    public JFXTextField txfTitleS;
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
    public HBox hbxSearchFromTo;
    @FXML
    public GridPane grdCreateCenter;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public HBox hbxShowDescriptionFooter;
    @FXML
    public DataTable<MyNoteFxModel> tblMyNote;
    @FXML
    public CustomTableColumn<MyNoteFxModel, String> colDescription;
    @FXML
    public CustomTableColumn<MyNoteFxModel, PersianDate> colCreateDate;
    @FXML
    public CustomTableColumn<MyNoteFxModel, PersianDate> colInActivationDate;
    @FXML
    public CustomTableColumn<MyNoteFxModel, RatingExtra> colPriority;
    @FXML
    public CustomTableColumn<MyNoteFxModel, String> colFullName;
    @FXML
    public CustomTableColumn<MyNoteFxModel, String> colIsActive;

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }

    int getMaxDay(PersianDate persianDate) {
        int monthNumber = persianDate.getMonth().getValue();

        if (monthNumber == 12) {
            return 29;
        }
        if (monthNumber <= 6) {
            return 31;
        }
        return 30;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        dlgShowDescription.setDialogContainer(startController.mainStack);
        applyFontStyle(dlgCreate);
        applyFontStyle(dlgDelete);
        applyFontStyle(dlgShowDescription);

        calNoteSearch.timeProperty().addListener((observable, oldValue, newValue) -> {
            PersianDate persianNewDate = PersianDate.fromGregorian(newValue);

            LocalDate minDate = PersianDate.of(persianNewDate.getYear(), persianNewDate.getMonth(), 1).toGregorian();
            LocalDate maxDate = PersianDate.of(persianNewDate.getYear(), persianNewDate.getMonth(), getMaxDay(persianNewDate)).toGregorian();

            calNoteSearch.getEventList().clear();
            myNoteService.getCalenderNoteStatuses(minDate, maxDate, MyNoteCategoryController.myNoteCategoryId).forEach(calenderNoteStatus -> {
                if (calenderNoteStatus.getInActiveCount() != 0) {
                    calNoteSearch.getEventList().add(new CalenderEvent(calenderNoteStatus.getCreationTime(), calenderNoteStatus.getInActiveCount() + " : " + MessageUtils.Message.INACTIVE));
                }

                if (calenderNoteStatus.getActiveCount() != 0) {
                    calNoteSearch.getEventList().add(new CalenderEvent(calenderNoteStatus.getCreationTime(), calenderNoteStatus.getActiveCount() + " : " + MessageUtils.Message.ACTIVE));
                }


            });
        });

        refreshCalender();

        //
        sdpCreationDateC.setDialogContainer(startController.mainStack);
        sdpCreationDateFromS.setDialogContainer(startController.mainStack);
        sdpCreationDateToS.setDialogContainer(startController.mainStack);

        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));

        autPersonS.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());
        autPersonC.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());


        autPersonC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                autPersonC.validate();
            }
        });

        autPersonC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PERSON));

        tblMyNote.setOnEdit(selectedItem -> {
            MyNoteFxModel editModel = myNoteService.load(selectedItem.getId(), MyNoteCategoryController.myNoteCategoryId).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            txfTitleC.setText(editModel.getTitle());
            autPersonC.setValue(editModel.getPerson());
            sdpCreationDateC.setValue(editModel.getCreationDate());
            txaDescriptionC.setText(editModel.getDescription());
            chbIsActiveC.setVisible(true);
            chbIsActiveC.setSelected(editModel.getIsActive());
            rtnPriorityC.setRating(editModel.getPriority());
            dlgCreate.show();

        });

        tblMyNote.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    selectedIds.forEach(myNoteService::delete);
                    Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable(true);
                    refreshCalender();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        tblMyNote.setOnExtra(selectedIds -> {
            try {
                myNoteTempService.sendToTemp(selectedIds);
                Notify.showSuccessMessage(MessageUtils.Message.ADD_TO_MY_NOTE_TEMP);
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }
        });

        tblMyNote.setOnVisit(selectedItem -> {
            myNoteService.load(selectedItem.getId(), MyNoteCategoryController.myNoteCategoryId).map(mapper::entityToModel).ifPresent(loadedModel -> {
                lblTitleShow.setText(loadedModel.getTitle());
                txaDescriptionShow.setEditable(false);
                txaDescriptionShow.setText(loadedModel.getDescription());
                dlgShowDescription.show();
            });
        });
        tblMyNote.setItemPage(pageRequest -> myNoteService.loadItem(searchModel,pageRequest));
        updateDataTable(true);

        chbIsActiveC.setMinWidth(ScreenUtils.getActualSize(300));

        cmbIsActiveS.setValue(JFXActiveValue.NONE);
        cmbIsActiveS.setMinWidth(ScreenUtils.getActualSize(300));

        colDescription.setCellValueFactory(param -> new ReadOnlyObjectWrapper<String>(getRightDescription(param)));


        colPriority.setCellValueFactory(param -> new ReadOnlyObjectWrapper<RatingExtra>(new RatingExtra(null, param.getValue().getPriority(), null, true)));
        colPriority.setComparator(Comparator.comparing(RatingExtra::getRating));

        colCreateDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getCreationDate() == null ? null : PersianDate.fromGregorian(param.getValue().getCreationDate())));

        colInActivationDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getInActivationDate() == null ? null : PersianDate.fromGregorian(param.getValue().getInActivationDate())));

        colIsActive.setCellValueFactory(param -> new ReadOnlyObjectWrapper<String>(param.getValue().getIsActive() ? MessageUtils.Message.ACTIVE : MessageUtils.Message.INACTIVE));

        colFullName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPerson().getTitle()));

        tbpDateSearch.setStyle(new StringJoiner(" ; ")
                .add("-fx-border-width: " + ScreenUtils.getActualSize(3))
                .add("-fx-border-color: #757575")
                .add("-fx-border-radius: " + ScreenUtils.getActualSize(5))
                .add("-fx-background-radius: " + ScreenUtils.getActualSize(5))
                .toString());

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));
        autPersonC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PERSON));
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

        grdSearch.setPrefHeight(ScreenUtils.getActualSize(1200));
        grdSearch.setMinHeight(ScreenUtils.getActualSize(1200));
        grdSearch.setHgap(ScreenUtils.getActualSize(20));
        grdSearch.setVgap(ScreenUtils.getActualSize(50));
        grdSearch.setPadding(new Insets(ScreenUtils.getActualSize(42), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(25)));

        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));

        grdCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(25)));

        hbxSearchFromTo.setSpacing(ScreenUtils.getActualSize(50));


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

    private String getRightDescription(TableColumn.CellDataFeatures<MyNoteFxModel, String> param) {
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

    private void updateDataTable(boolean allActive) {
        searchModel.setAllActive(allActive);
        searchModel.setMyNoteCategoryId(MyNoteCategoryController.myNoteCategoryId);
        tblMyNote.refresh();
    }

    @FXML
    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    @FXML
    public void showCreateDialog(ActionEvent actionEvent) {
        createModel.setId(null);
        autPersonC.setValue(null);
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
        boolean personValidate = autPersonC.validate();

        return titleValidate && creationDateValidate && personValidate;
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        if (validateBeforeSave()) {
            try {
                createModel.setMyNoteCategory(new MyNoteCategoryFxModel(MyNoteCategoryController.myNoteCategoryId));
                myNoteService.saveOrUpdate(mapper.modelToEntity(createModel));
                dlgCreate.close();
                updateDataTable(true);

                refreshCalender();


                Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE + " " + MessageUtils.Message.SUCCESS_SAVE);
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }
        }
    }

    private void refreshCalender() {
        LocalDate temp = calNoteSearch.getTime();
        calNoteSearch.setTime(PersianDate.MIN.toGregorian());
        calNoteSearch.setTime(temp);
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        if (tbpDateSearch.getSelectionModel().getSelectedIndex() == 0) {
            searchModel.setCreationDate(calNoteSearch.getTime());
            sdpCreationDateFromS.setValue(null);
            sdpCreationDateToS.setValue(null);
        } else {
            searchModel.setCreationDate(null);
        }
        searchModel.setMyNoteCategoryId(MyNoteCategoryController.myNoteCategoryId);
        tblMyNote.refresh();
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfTitleS.setText(null);
        sdpCreationDateFromS.setValue(null);
        sdpCreationDateToS.setValue(null);
        txfDescriptionS.setText(null);
        autPersonS.setValue(null);
        cmbIsActiveS.setValue(JFXActiveValue.NONE);
        rtnPriorityS.setRating(0.0);
        updateDataTable(false);
    }

    @FXML
    public void closeShowDescriptionDialog(ActionEvent actionEvent) {
        dlgShowDescription.close();
    }
}
