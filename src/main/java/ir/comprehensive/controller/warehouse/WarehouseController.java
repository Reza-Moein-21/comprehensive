package ir.comprehensive.controller.warehouse;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.MultiAutocomplete;
import ir.comprehensive.component.PrintDialog;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.CustomTableColumn;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.mapper.WarehouseCategoryMapper;
import ir.comprehensive.mapper.WarehouseMapper;
import ir.comprehensive.mapper.WarehouseTagMapper;
import ir.comprehensive.fxmodel.WarehouseCategoryModel;
import ir.comprehensive.fxmodel.WarehouseModel;
import ir.comprehensive.fxmodel.WarehouseTagModel;
import ir.comprehensive.service.WarehouseCategoryService;
import ir.comprehensive.service.WarehouseService;
import ir.comprehensive.service.WarehouseTagService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class WarehouseController implements Initializable {

    @Autowired
    private WarehouseService warehouseService;
    @FXML
    public com.jfoenix.controls.JFXTextField txfTitleS;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCodeS;
    @FXML
    public MultiAutocomplete<WarehouseTagModel> mauTagListS;
    @Autowired
    private StartController startController;
    @Autowired
    private WarehouseMapper mapper;
    @Autowired
    private WarehouseTagService warehouseTagService;

    public CustomTableColumn<WarehouseModel, String> colTag;

    @FXML
    public JFXTextField txfDescriptionD;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCountD;
    @FXML
    public com.jfoenix.controls.JFXTextField txfTitleD;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCompanyNameD;
    @FXML
    public com.jfoenix.controls.JFXTextField txtTagListD;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCodeD;
    @FXML
    public GridPane grdMainCreate;
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
    @Autowired
    private WarehouseTagMapper tagMapper;
    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;
    @FXML
    public VBox parent;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCompanyNameS;
    @FXML
    public Autocomplete<WarehouseCategoryModel> autCategoryS;
    @FXML
    public VBox vbxDisplayContent;
    @FXML
    public HBox hbxDisplayHeader;
    @FXML
    public JFXDialog dlgDisplay;
    @FXML
    public com.jfoenix.controls.JFXTextField txfTitleC;
    @FXML
    public Autocomplete<WarehouseCategoryModel> autCategoryC;
    @FXML
    public MultiAutocomplete<WarehouseTagModel> mauTagListC;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCodeC;
    @FXML
    public GridPane grdDisplayMain;
    @FXML
    public com.jfoenix.controls.JFXTextField txtCompanyNameC;
    @FXML
    public JFXTextField txtCountC;
    @Autowired
    private WarehouseCategoryService warehouseCategoryService;
    @Autowired
    private WarehouseCategoryMapper warehouseCategoryMapper;
    @FXML
    public com.jfoenix.controls.JFXTextField txfDescriptionC;
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public HBox hbxDisplayFooter;
    @FXML
    public DataTable<WarehouseModel> tblWarehouse;

    @FXML
    private WarehouseModel createModel;
    @FXML
    private WarehouseModel searchModel;

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

        colTag.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTagList().stream().map(tagModel -> String.format("[ %s ] ", tagModel.getTitle())).collect(Collectors.joining())));

        parent.setSpacing(ScreenUtils.getActualSize(10));

        mauTagListC.setOnSearch(s -> warehouseTagService.findByTitle(s).map(warehouseTags -> warehouseTags.stream().map(tagMapper::entityToModel).collect(Collectors.toList())).get());
        mauTagListS.setOnSearch(s -> warehouseTagService.findByTitle(s).map(warehouseTags -> warehouseTags.stream().map(tagMapper::entityToModel).collect(Collectors.toList())).get());

        grdMainCreate.setHgap(ScreenUtils.getActualSize(50));
        grdMainCreate.setVgap(ScreenUtils.getActualSize(100));
        //
        vbxDisplayContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxDisplayContent.setPrefWidth(ScreenUtils.getActualSize(1900));
        //
        hbxDisplayHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        grdDisplayMain.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdDisplayMain.setHgap(ScreenUtils.getActualSize(10));
        grdDisplayMain.setVgap(ScreenUtils.getActualSize(100));
        hbxDisplayFooter.setPadding(new Insets(ScreenUtils.getActualSize(20)));
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
        grdSearchContent.setPrefHeight(ScreenUtils.getActualSize(600));
        grdSearchContent.setHgap(ScreenUtils.getActualSize(20));
        grdSearchContent.setVgap(ScreenUtils.getActualSize(25));
        grdSearchContent.setPadding(new Insets(ScreenUtils.getActualSize(20)));

        //
        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        autCategoryC.setOnSearch(s -> warehouseCategoryService.findByTitle(s).map(warehouseCategories -> warehouseCategories.stream().map(warehouseCategoryMapper::entityToModel).collect(Collectors.toList())).get());
        autCategoryS.setOnSearch(s -> warehouseCategoryService.findByTitle(s).map(warehouseCategories -> warehouseCategories.stream().map(warehouseCategoryMapper::entityToModel).collect(Collectors.toList())).get());

        tblWarehouse.setOnEdit(selectedItem -> {
            WarehouseModel editModel = warehouseService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            txfTitleC.setText(editModel.getTitle());
            autCategoryC.setValue(editModel.getCategory());
            mauTagListC.setValueList(FXCollections.observableArrayList(editModel.getTagList()));
            txtCodeC.setText(editModel.getCode());
            txtCompanyNameC.setText(editModel.getCompanyName());
            txtCountC.setText(String.valueOf(editModel.getCount()));
            txfDescriptionC.setText(editModel.getDescription());
            dlgCreate.show();

        });

        tblWarehouse.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    selectedIds.forEach(warehouseService::delete);
                    Notify.showSuccessMessage(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_DELETE);
                    tblWarehouse.refresh();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        tblWarehouse.setOnVisit(selectedItem -> {
            txfTitleD.setText(selectedItem.getTitle());
            txtCodeD.setText(selectedItem.getCode());
            txtCountD.setText(String.valueOf(selectedItem.getCount()));
            txtCompanyNameD.setText(selectedItem.getCompanyName());
            txtTagListD.setText(selectedItem.getTagList().stream().map(tagModel -> String.format("[ %s ] ", tagModel.getTitle())).collect(Collectors.joining()));
            txfDescriptionD.setText(selectedItem.getDescription());
            dlgDisplay.show();
        });

        tblWarehouse.setOnPrint(selectedIds -> {
            PrintDialog printDialog = new PrintDialog();
            printDialog.setDialogContainer(startController.mainStack);
            applyFontStyle(printDialog);
            printDialog.show();
            printDialog.setOnPrintConfirm(printModel -> {
                try {
                    Map<String, Object> params = new HashMap<>();
                    params.put("printTitle", printModel.getTitle() == null ? "" : printModel.getTitle());
                    ReportUtils.print("report/warehouse.jrxml", printModel.getDestinationPath(), params, warehouseService.getReportBeanList(searchModel, selectedIds), printModel.getType());
                    Notify.showSuccessMessage(MessageUtils.Message.PRINT + " " + MessageUtils.Message.SUCCESS_DONE);
                } catch (Exception e) {
                    Notify.showErrorMessage(e.getMessage());
                } finally {
                    printDialog.close();
                }

            });

        });

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));
        txtCodeC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.CODE));
        txtCountC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.COUNT));
        txtCountC.getValidators().add(FormValidationUtils.getCountValidator(MessageUtils.Message.COUNT));
        tblWarehouse.setItemPage(pageRequest -> warehouseService.loadItem(searchModel,pageRequest));
        tblWarehouse.refresh();
    }


    public void search() {
        tblWarehouse.refresh();
    }

    public void showCreateDialog() {
        createModel.setId(null);
        txfTitleC.setText(null);
        autCategoryC.setValue(null);
        mauTagListC.setValueList(null);
        txtCodeC.setText(null);
        txtCompanyNameC.setText(null);
        txtCountC.setText(null);
        txfDescriptionC.setText(null);
        txfDescriptionC.setText(null);
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    public void closeDisplayDialog(ActionEvent actionEvent) {
        dlgDisplay.close();
    }

    private boolean validateBeforeSave() {
        boolean titleValidate = txfTitleC.validate();
        boolean codeValidate = txtCodeC.validate();
        boolean countValidate = txtCountC.validate();
        return titleValidate && codeValidate && countValidate;
    }

    public void save() {
        if (!validateBeforeSave()) {
            return;
        }
        try {
            List<WarehouseTagModel> collect = createModel.getTagList().stream().map(warehouseTag -> {
                WarehouseTagModel myTag = new WarehouseTagModel();
                if (warehouseTag.getId() != null && warehouseTag.getId() > 0) {
                    myTag.setId(warehouseTag.getId());
                }
                myTag.setTitle(warehouseTag.getTitle());
                return myTag;
            }).collect(Collectors.toList());

            createModel.getTagList().clear();
            createModel.getTagList().addAll(collect);
            warehouseService.saveOrUpdate(mapper.modelToEntity(createModel));
            dlgCreate.close();
            tblWarehouse.refresh();
            Notify.showSuccessMessage(MessageUtils.Message.WAREHOUSE + " " + MessageUtils.Message.SUCCESS_SAVE);
        } catch (GeneralException e) {
            Notify.showErrorMessage(e.getMessage());
        }

    }

    public void showAll(ActionEvent actionEvent) {
        txfTitleS.setText(null);
        autCategoryS.setValue(null);
        mauTagListS.setValueList(null);
        txtCodeS.setText(null);
        txtCompanyNameS.setText(null);
        tblWarehouse.refresh();    }
}
