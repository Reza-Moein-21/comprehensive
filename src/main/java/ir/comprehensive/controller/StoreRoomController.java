package ir.comprehensive.controller;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.model.ProductModel;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class StoreRoomController implements Initializable {

    public VBox vbxCreateCenter;
    public HBox hbxCreateFooter;
    public GridPane grdSearchContent;
    public JFXButton btnCreate;

    @Autowired
    private ProductDeliveryService productDeliveryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StartController startController;
    @Autowired
    private ProductDeliveryMapper mapper;
    @Autowired
    private PersonMapper personMapper;


    @FXML
    public VBox vbxCreateContent;
    @FXML
    public HBox hbxCreateHeader;

    @FXML
    public JFXComboBox<ProductStatus> cmbStatusS;
    @FXML
    public SimpleDatePicker sdpDeliveryDateFromS;
    @FXML
    public SimpleDatePicker sdpDeliveryDateToS;
    @FXML
    public SimpleDatePicker sdpReceivedDateFromS;
    @FXML
    public SimpleDatePicker sdpReceivedDateToS;
    @FXML
    public JFXTextField txfProductNameS;
    @FXML
    public Autocomplete<PersonModel> autPersonS;
    @FXML
    public HBox receivedDateFromToHBox;

    @FXML
    public SimpleDatePicker sdpReceivedDateC;
    @FXML
    public JFXTextField txfDescriptionC;
    @FXML
    public SimpleDatePicker sdpDesiredDateC;
    @FXML
    public SimpleDatePicker sdpDeliveryDateC;
    @FXML
    public JFXTextField txfProductNameC;
    @FXML
    public Autocomplete<PersonModel> autPersonC;
    @FXML
    public JFXComboBox<ProductStatus> cmbStatusC;

    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public DataTable<ProductDeliveryModel> tblProductDelivery;
    @FXML
    public TableColumn<ProductDeliveryModel, String> colProductName;
    @FXML
    public TableColumn<ProductDeliveryModel, String> colFullName;
    @FXML
    public TableColumn<ProductDeliveryModel, ProductStatus> colStatus;
    @FXML
    public TableColumn<ProductDeliveryModel, PersianDate> colDeliveryDate;
    @FXML
    public TableColumn<ProductDeliveryModel, PersianDate> colDesiredDate;
    @FXML
    public TableColumn<ProductDeliveryModel, PersianDate> colReceivedDate;
    @FXML
    public TableColumn<ProductDeliveryModel, String> colDescription;

    private void fillDataTable() {
        tblProductDelivery.setItems(productDeliveryService.loadByStatus(cmbStatusS.getValue()).map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }

    @FXML
    private ProductDeliveryModel createModel;
    @FXML
    private ProductDeliveryModel searchModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        //init Table
        colProductName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getProduct().getTitle()));
        colProductName.setMinWidth(ScreenUtils.getActualSize(200));
        colProductName.setPrefWidth(ScreenUtils.getActualSize(400));

        colFullName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPerson().getTitle()));
        colFullName.setMinWidth(ScreenUtils.getActualSize(200));
        colFullName.setPrefWidth(ScreenUtils.getActualSize(400));

        colStatus.setCellValueFactory(new PropertyValueFactory<ProductDeliveryModel, ProductStatus>("status"));
        colStatus.setMinWidth(ScreenUtils.getActualSize(200));
        colStatus.setPrefWidth(ScreenUtils.getActualSize(400));

        colDeliveryDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getDeliveryDate() == null ? null : PersianDate.fromGregorian(param.getValue().getDeliveryDate())));
        colDeliveryDate.setMinWidth(ScreenUtils.getActualSize(200));
        colDeliveryDate.setPrefWidth(ScreenUtils.getActualSize(400));

        colDesiredDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getDesiredDate() == null ? null : PersianDate.fromGregorian(param.getValue().getDesiredDate())));
        colDesiredDate.setMinWidth(ScreenUtils.getActualSize(200));
        colDesiredDate.setPrefWidth(ScreenUtils.getActualSize(400));

        colReceivedDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getReceivedDate() == null ? null : PersianDate.fromGregorian(param.getValue().getReceivedDate())));
        colReceivedDate.setMinWidth(ScreenUtils.getActualSize(200));
        colReceivedDate.setPrefWidth(ScreenUtils.getActualSize(400));


        colDescription.setCellValueFactory(new PropertyValueFactory<ProductDeliveryModel, String>("description"));
        colDescription.setMinWidth(ScreenUtils.getActualSize(200));
        colDescription.setPrefWidth(ScreenUtils.getActualSize(400));

        //
        sdpDeliveryDateFromS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpDeliveryDateToS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpReceivedDateFromS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpReceivedDateToS.setPrefWidth(ScreenUtils.getActualSize(500));
        //
        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1024));
        //
        hbxCreateHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        //
        vbxCreateCenter.setPadding(new Insets(0, ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10)));
        vbxCreateCenter.setSpacing(ScreenUtils.getActualSize(100));
        //
        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        //
        autPersonC.setPrefWidth(ScreenUtils.getActualSize(500));
        //
        sdpDeliveryDateC.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpDesiredDateC.setPrefWidth(ScreenUtils.getActualSize(500));
        //
        cmbStatusC.setPrefWidth(ScreenUtils.getActualSize(500));
        cmbStatusS.setPrefWidth(ScreenUtils.getActualSize(500));
        //
        cmbStatusC.setPrefWidth(ScreenUtils.getActualSize(500));

        //
        grdSearchContent.setPrefHeight(ScreenUtils.getActualSize(500));
        grdSearchContent.setHgap(ScreenUtils.getActualSize(20));
        grdSearchContent.setVgap(ScreenUtils.getActualSize(30));
        grdSearchContent.setPadding(new Insets(ScreenUtils.getActualSize(50), 0, 0, 0));
        //
        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));

        txfProductNameC.setPrefWidth(ScreenUtils.getActualSize(500));
        tblProductDelivery.setOnEdit(selectedItem -> {
            ProductDeliveryModel editModel = productDeliveryService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            editModel.getPerson().getTitle();
            autPersonC.setValue(editModel.getPerson());
            txfProductNameC.setText(editModel.getProduct().getTitle());
            sdpDeliveryDateC.setValue(editModel.getDeliveryDate());
            sdpDesiredDateC.setValue(editModel.getDesiredDate());
            txfDescriptionC.setText(editModel.getDescription());
            cmbStatusC.setValue(editModel.getStatus());
            cmbStatusC.setDisable(false);
            sdpReceivedDateC.setValue(editModel.getReceivedDate());
            dlgCreate.show();

        });

        tblProductDelivery.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    productDeliveryService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_DELETE);
                    fillDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        autPersonC.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());


        autPersonC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                autPersonC.validate();
            }
        });
        txfProductNameC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfProductNameC.validate();
            }
        });

        autPersonC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PERSON));
        txfProductNameC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PRODUCT_NAME));
        sdpDeliveryDateC.setValidators(Arrays.asList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.DELIVERY_DATE), FormValidationUtils.getMaxDateValidator(MessageUtils.Message.DELIVERY_DATE, LocalDate.now(), MessageUtils.Message.TODAY_DATE)));
        sdpReceivedDateC.setValidators(Collections.singletonList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.RECEIVED_DATE)));


        autPersonS.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());


        sdpDeliveryDateC.setDialogContainer(startController.mainStack);
        sdpDesiredDateC.setDialogContainer(startController.mainStack);
        sdpReceivedDateC.setDialogContainer(startController.mainStack);
        sdpDeliveryDateFromS.setDialogContainer(startController.mainStack);
        sdpDeliveryDateToS.setDialogContainer(startController.mainStack);
        sdpReceivedDateFromS.setDialogContainer(startController.mainStack);
        sdpReceivedDateToS.setDialogContainer(startController.mainStack);

        cmbStatusS.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null && newValue.equals(ProductStatus.RECEIVED)) {
                        receivedDateFromToHBox.setVisible(true);
                    } else {
                        sdpReceivedDateFromS.setValue(null);
                        sdpReceivedDateToS.setValue(null);
                        receivedDateFromToHBox.setVisible(false);
                    }
                }
        );

        cmbStatusC.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null && newValue.equals(ProductStatus.RECEIVED)) {
                        sdpReceivedDateC.setVisible(true);
                    } else {
                        sdpReceivedDateC.setValue(null);
                        sdpReceivedDateC.setVisible(false);
                    }
                }
        );
        cmbStatusS.setValue(ProductStatus.UNKNOWN);
        fillDataTable();
    }


    public void search(ActionEvent actionEvent) {
        searchModel.setProduct(new ProductModel(txfProductNameS.getText()));
        tblProductDelivery.setItems(productDeliveryService.search(searchModel).map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }

    public void showCreateDialog() {
        createModel.setId(null);
        autPersonC.setValue(null);
        txfProductNameC.setText("");
        txfDescriptionC.setText("");
        sdpDeliveryDateC.setValue(null);
        sdpDesiredDateC.setValue(null);
        cmbStatusC.setValue(ProductStatus.UNKNOWN);
        cmbStatusC.setDisable(true);
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean personValidate = autPersonC.validate();
        boolean productNameValidate = txfProductNameC.validate();
        boolean deliveryDateValidate = sdpDeliveryDateC.validate();
        boolean receivedDateValidate = true;
        if (cmbStatusC.getValue().equals(ProductStatus.RECEIVED)) {
            receivedDateValidate = sdpReceivedDateC.validate();
        }
        return personValidate && productNameValidate && deliveryDateValidate && receivedDateValidate;
    }

    public void save() {
        if (!validateBeforeSave()) {
            return;
        }
        createModel.setProduct(new ProductModel(txfProductNameC.getText()));

        try {
            productDeliveryService.saveOrUpdate(mapper.modelToEntity(createModel));
            dlgCreate.close();
            fillDataTable();
            Notify.showSuccessMessage(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_SAVE);
        } catch (GeneralException e) {
            Notify.showErrorMessage(e.getMessage());
        }

    }

    public void showAll(ActionEvent actionEvent) {
        cmbStatusS.setValue(null);
        tblProductDelivery.setItems(productDeliveryService.loadAll().map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }
}
