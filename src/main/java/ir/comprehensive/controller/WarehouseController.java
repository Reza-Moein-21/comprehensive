package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.MultiAutocomplete;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.mapper.WarehouseCategoryMapper;
import ir.comprehensive.mapper.WarehouseMapper;
import ir.comprehensive.mapper.WarehouseTagMapper;
import ir.comprehensive.model.WarehouseCategoryModel;
import ir.comprehensive.model.WarehouseModel;
import ir.comprehensive.model.WarehouseTagModel;
import ir.comprehensive.service.WarehouseCategoryService;
import ir.comprehensive.service.WarehouseService;
import ir.comprehensive.service.WarehouseTagService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class WarehouseController implements Initializable {

    @Autowired
    private WarehouseService warehouseService;
    @FXML
    public JFXTextField txfTitleS;
    @FXML
    public JFXTextField txtCodeS;
    @FXML
    public MultiAutocomplete<WarehouseTagModel> mauTagListS;
    @Autowired
    private StartController startController;
    @Autowired
    private WarehouseMapper mapper;
    @Autowired
    private WarehouseTagService warehouseTagService;


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
    public JFXTextField txtCompanyNameS;
    @FXML
    public Autocomplete<WarehouseCategoryModel> autCategoryS;
    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public Autocomplete<WarehouseCategoryModel> autCategoryC;
    @FXML
    public MultiAutocomplete<WarehouseTagModel> mauTagListC;
    @FXML
    public JFXTextField txtCodeC;
    @FXML
    public JFXTextField txtCompanyNameC;
    @FXML
    public JFXTextField txtCountC;
    @Autowired
    private WarehouseCategoryService warehouseCategoryService;
    @Autowired
    private WarehouseCategoryMapper warehouseCategoryMapper;
    @FXML
    public JFXTextField txfDescriptionC;
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public DataTable<WarehouseModel> tblWarehouse;

    private void fillDataTable() {
        tblWarehouse.setItems(warehouseService.loadAll().map(warehouses -> warehouses.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }

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
        applyFontStyle(dlgCreate);
        applyFontStyle(dlgDelete);

        parent.setSpacing(ScreenUtils.getActualSize(10));

        mauTagListC.setOnSearch(s -> warehouseTagService.findByTitle(s).map(warehouseTags -> warehouseTags.stream().map(tagMapper::entityToModel).collect(Collectors.toList())).get());
        mauTagListS.setOnSearch(s -> warehouseTagService.findByTitle(s).map(warehouseTags -> warehouseTags.stream().map(tagMapper::entityToModel).collect(Collectors.toList())).get());

        grdMainCreate.setHgap(ScreenUtils.getActualSize(50));
        grdMainCreate.setVgap(ScreenUtils.getActualSize(50));

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

        tblWarehouse.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    warehouseService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_DELETE);
                    fillDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        fillDataTable();
    }


    public void search() {
        tblWarehouse.setItems(warehouseService.search(searchModel).map(warehouses -> warehouses.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
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

    private boolean validateBeforeSave() {
        return true;
    }

    public void save() {
        if (!validateBeforeSave()) {
            return;
        }
        try {
            warehouseService.saveOrUpdate(mapper.modelToEntity(createModel));
            dlgCreate.close();
            fillDataTable();
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
        tblWarehouse.setItems(warehouseService.loadAll().map(warehouses -> warehouses.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }
}
