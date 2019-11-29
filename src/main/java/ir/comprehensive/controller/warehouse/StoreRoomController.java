package ir.comprehensive.controller.warehouse;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.mapper.WarehouseMapper;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.model.WarehouseModel;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import ir.comprehensive.service.WarehouseService;
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
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class StoreRoomController implements Initializable {


    private static Long currentCount;
    private static WarehouseModel currentProduct;
    public JFXDialog dlgDisplay;
    public VBox vbxDisplayContent;
    public HBox hbxDisplayHeader;
    public GridPane grdDisplayMain;
    public JFXTextField txfProductNameD;
    public JFXTextField txfFullNameD;
    public JFXTextField txfStatusD;
    public JFXTextField txfCountD;
    public JFXTextField txfDeliveryDateD;
    public JFXTextField txfDesiredDateD;
    public JFXTextField txfReceivedDateD;
    @Autowired
    private ProductDeliveryService productDeliveryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StartController startController;
    @FXML
    public Autocomplete<WarehouseModel> autProductNameS;
    @FXML
    public Autocomplete<WarehouseModel> autProductNameC;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private PersonMapper personMapper;
    public JFXTextField txfDescriptionD;
    @FXML
    public JFXTextField txfCountC;

    @FXML
    public GridPane grdPersonProduct;
    @FXML
    public GridPane grdDeliverDesiredDate;
    @FXML
    public GridPane grdStatusReceivedDate;
    @FXML
    public VBox vbxCreateContent;
    @FXML
    public HBox hbxCreateHeader;
    @FXML
    public VBox vbxCreateCenter;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public GridPane grdSearchContent;
    @FXML
    public JFXButton btnCreate;

    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;
    @FXML
    public VBox parent;
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
    @Autowired
    private WarehouseMapper warehouseMapper;
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
    @Autowired
    private ProductDeliveryMapper mapper;
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
    public TableColumn<ProductDeliveryModel, PersianDate> colDeliveryDate;
    @FXML
    public TableColumn<ProductDeliveryModel, PersianDate> colDesiredDate;
    @FXML
    public TableColumn<ProductDeliveryModel, PersianDate> colReceivedDate;
    @FXML
    public TableColumn<ProductDeliveryModel, String> colDescription;
    public HBox hbxDisplayFooter;

    private void fillDataTable() {
        tblProductDelivery.setItems(productDeliveryService.loadByStatus(cmbStatusS.getValue()).map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }

    @FXML
    private ProductDeliveryModel createModel;
    @FXML
    private ProductDeliveryModel searchModel;

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        dlgDisplay.setDialogContainer(startController.mainStack);
        applyFontStyle(dlgCreate);
        applyFontStyle(dlgDelete);
        applyFontStyle(dlgDisplay);

        parent.setSpacing(ScreenUtils.getActualSize(10));

        //init Table
        colProductName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getProduct().getCode() + " : " + param.getValue().getProduct().getTitle()));

        colFullName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPerson().getTitle()));

        hbxDisplayHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        grdDisplayMain.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdDisplayMain.setHgap(ScreenUtils.getActualSize(10));
        grdDisplayMain.setVgap(ScreenUtils.getActualSize(100));


        hbxDisplayFooter.setPadding(new Insets(ScreenUtils.getActualSize(20)));


        vbxDisplayContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxDisplayContent.setPrefWidth(ScreenUtils.getActualSize(1900));

        colDeliveryDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getDeliveryDate() == null ? null : PersianDate.fromGregorian(param.getValue().getDeliveryDate())));

        colDesiredDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getDesiredDate() == null ? null : PersianDate.fromGregorian(param.getValue().getDesiredDate())));

        colReceivedDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PersianDate>(param.getValue().getReceivedDate() == null ? null : PersianDate.fromGregorian(param.getValue().getReceivedDate())));

        grdPersonProduct.setHgap(ScreenUtils.getActualSize(15));
        grdDeliverDesiredDate.setHgap(ScreenUtils.getActualSize(15));
        grdStatusReceivedDate.setHgap(ScreenUtils.getActualSize(15));

        //
        sdpDeliveryDateFromS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpDeliveryDateToS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpReceivedDateFromS.setPrefWidth(ScreenUtils.getActualSize(500));
        sdpReceivedDateToS.setPrefWidth(ScreenUtils.getActualSize(500));
        //
        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1600));
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
        grdSearchContent.setPadding(new Insets(ScreenUtils.getActualSize(42), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(25)));

        receivedDateFromToHBox.setSpacing(ScreenUtils.getActualSize(10));
        //
        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        autProductNameC.setPrefWidth(ScreenUtils.getActualSize(500));
        tblProductDelivery.setOnEdit(selectedItem -> {
            ProductDeliveryModel editModel = productDeliveryService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            editModel.getPerson().getTitle();
            autPersonC.setValue(editModel.getPerson());
            autProductNameC.setValue(editModel.getProduct());
            sdpDeliveryDateC.setValue(editModel.getDeliveryDate());
            sdpDesiredDateC.setValue(editModel.getDesiredDate());
            txfDescriptionC.setText(editModel.getDescription());
            txfCountC.setText(String.valueOf(editModel.getCount()));
            currentCount = editModel.getCount();
            currentProduct = editModel.getProduct();
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
        tblProductDelivery.setOnVisit(selectedItem -> {
            txfProductNameD.setText(selectedItem.getProduct().getCode() + " : " + selectedItem.getProduct().getTitle());
            txfFullNameD.setText(selectedItem.getPerson().toString());
            txfStatusD.setText(selectedItem.getStatus().toString());
            txfCountD.setText(String.valueOf(selectedItem.getCount()));
            txfDeliveryDateD.setText(selectedItem.getDeliveryDate() != null ? PersianDate.fromGregorian(selectedItem.getDeliveryDate()).toString() : "");
            txfDesiredDateD.setText(selectedItem.getDesiredDate() != null ? PersianDate.fromGregorian(selectedItem.getDesiredDate()).toString() : "");
            txfReceivedDateD.setText(selectedItem.getReceivedDate() != null ? PersianDate.fromGregorian(selectedItem.getReceivedDate()).toString() : "");
            txfDescriptionD.setText(selectedItem.getDescription());
            dlgDisplay.show();
        });
        autPersonC.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());
        autProductNameC.setOnSearch(s -> warehouseService.findByName(s).map(warehouseList -> warehouseList.stream().map(warehouseMapper::entityToModel).collect(Collectors.toList())).get());


        autPersonC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                autPersonC.validate();
            }
        });
        // todo must complete later....
        //        txfCountC.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue && !isNotValidateCount()) {
//                txfCountC.validate();
//            }
//        });
        autProductNameC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                autProductNameC.validate();
            }
        });

        autPersonC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PERSON));
        autProductNameC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PRODUCT_NAME));
        sdpDeliveryDateC.setValidators(Arrays.asList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.DELIVERY_DATE), FormValidationUtils.getMaxDateValidator(MessageUtils.Message.DELIVERY_DATE, LocalDate.now(), MessageUtils.Message.TODAY_DATE)));
        sdpReceivedDateC.setValidators(Arrays.asList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.DELIVERY_DATE), FormValidationUtils.getMaxDateValidator(MessageUtils.Message.DELIVERY_DATE, LocalDate.now(), MessageUtils.Message.TODAY_DATE)));
        txfCountC.setValidators(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.COUNT), FormValidationUtils.getCountValidator(MessageUtils.Message.COUNT), FormValidationUtils.getMaxNumberValidator(createModel.getProduct() != null ? createModel.getProduct().getCount() : null));

        autPersonS.setOnSearch(s -> personService.findByName(s).map(people -> people.stream().map(personMapper::entityToModel).collect(Collectors.toList())).get());
        autProductNameS.setOnSearch(s -> warehouseService.findByName(s).map(warehouses -> warehouses.stream().map(warehouseMapper::entityToModel).collect(Collectors.toList())).get());

        sdpDeliveryDateC.setDialogContainer(startController.mainStack);
        sdpDesiredDateC.setDialogContainer(startController.mainStack);
        sdpReceivedDateC.setDialogContainer(startController.mainStack);
        sdpDeliveryDateFromS.setDialogContainer(startController.mainStack);
        sdpDeliveryDateToS.setDialogContainer(startController.mainStack);
        sdpReceivedDateFromS.setDialogContainer(startController.mainStack);
        sdpReceivedDateToS.setDialogContainer(startController.mainStack);

        createModel.productProperty().addListener((observable, oldValue, newValue) -> {

            txfCountC.setValidators(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.COUNT), FormValidationUtils.getCountValidator(MessageUtils.Message.COUNT), FormValidationUtils.getMaxNumberValidator(newValue != null ? newValue.getCount() : null));

        });
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


            if (mustDisableCount(newValue)) {
                autProductNameC.setDisable(true);
                autProductNameC.setValue(currentProduct);

                txfCountC.setDisable(true);
                txfCountC.setText(String.valueOf(currentCount));
            } else {
                txfCountC.setDisable(false);
                autProductNameC.setDisable(false);
            }

                }
        );
        cmbStatusS.setValue(ProductStatus.UNKNOWN);
        fillDataTable();
    }

    private boolean mustDisableCount(ProductStatus newValue) {
        return !(newValue != null && newValue.equals(ProductStatus.UNKNOWN));
    }


    public void search(ActionEvent actionEvent) {
        tblProductDelivery.setItems(productDeliveryService.search(searchModel).map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).orElse(null));
    }

    public void showCreateDialog() {
        createModel.setId(null);
        autPersonC.setValue(null);
        autProductNameC.setValue(null);
        txfDescriptionC.setText("");
        sdpDeliveryDateC.setValue(null);
        sdpDesiredDateC.setValue(null);
        txfCountC.setText("");
        cmbStatusC.setValue(ProductStatus.UNKNOWN);
        cmbStatusC.setDisable(true);
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean personValidate = autPersonC.validate();
        boolean productNameValidate = autProductNameC.validate();
        boolean deliveryDateValidate = sdpDeliveryDateC.validate();
        boolean countValidate = isNotValidateCount() || txfCountC.validate();
        boolean receivedDateValidate = true;
        if (cmbStatusC.getValue().equals(ProductStatus.RECEIVED)) {
            receivedDateValidate = sdpReceivedDateC.validate();
        }
        return personValidate && productNameValidate && deliveryDateValidate && countValidate && receivedDateValidate;
    }

    private boolean isNotValidateCount() {
        boolean result;
        try {
            long selectedCount = Long.parseLong(txfCountC.getText());
            result = createModel.getId() != null && (selectedCount < currentCount || selectedCount == currentCount || selectedCount <= (currentCount + currentProduct.getCount()));

        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public void save() {
        if (!validateBeforeSave()) {
            return;
        }
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
        autPersonS.setValue(null);
        autProductNameS.setValue(null);
        sdpReceivedDateToS.setValue(null);
        sdpReceivedDateFromS.setValue(null);
        sdpDeliveryDateToS.setValue(null);
        sdpDeliveryDateFromS.setValue(null);
        cmbStatusS.setValue(null);
        tblProductDelivery.setItems(productDeliveryService.loadAll().map(productDeliveries -> productDeliveries.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).orElse(null));
    }

    public void closeDisplayDialog(ActionEvent actionEvent) {
        dlgDisplay.close();
    }
}
