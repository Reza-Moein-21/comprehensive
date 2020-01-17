package ir.comprehensive.controller;

import ir.comprehensive.component.Card;
import ir.comprehensive.model.HumanResourceInfo;
import ir.comprehensive.model.MyNoteCategoryInfo;
import ir.comprehensive.model.WarehouseInfo;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.MyNoteCategoryService;
import ir.comprehensive.service.WarehouseService;
import ir.comprehensive.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HomeController implements Initializable {
    @FXML
    public Card crdWarehouseHome;
    @FXML
    public Card crdHumanResource;
    @FXML
    public Card crdMyNoteCategory;
    @FXML
    public FlowPane masonry;
    @FXML
    public GridPane grdMyNoteCategoryContent;
    @FXML
    public GridPane grdHumanResourceContent;
    @FXML
    public GridPane grdWarehouseHomeContent;

    public MyNoteCategoryInfo myNoteCategoryInfo = new MyNoteCategoryInfo();
    public HumanResourceInfo humanResourceInfo = new HumanResourceInfo();

    public Label lblTotalWarehouse;
    public Label lblLostCount;
    public Label lblUnknownCount;
    public Label lblReceivedCount;
    public Label lblRejectedCount;
    public Label lblTotalMyNoteCategory;
    public Label lblInProgressCount;
    public Label lblDoneCount;
    public Label lblStoppedCount;
    public Label lblPersonCount;
    public Label lblCategoryCount;
    private WarehouseInfo warehouseInfo = new WarehouseInfo();

    @FXML
    VBox homePage;
    private StartController startController;
    private MyNoteCategoryService myNoteCategoryService;
    private CategoryService categoryService;
    private WarehouseService warehouseService;

    public HomeController(StartController startController, MyNoteCategoryService myNoteCategoryService, CategoryService categoryService, WarehouseService warehouseService) {
        this.startController = startController;
        this.myNoteCategoryService = myNoteCategoryService;
        this.categoryService = categoryService;
        this.warehouseService = warehouseService;
    }

    @FXML
    public void goToStoreroom() {
        startController.navigateToView(ViewName.STORE_ROOM);
    }

    @FXML
    public void goToWarehouseHome() {
        startController.navigateToView(ViewName.WAREHOUSE_HOME);
    }

    @FXML
    public void goToHumanResource() {
        startController.navigateToView(ViewName.HUMAN_RESOURCE);
    }

    @FXML
    public void goToMyNoteCategory() {
        startController.navigateToView(ViewName.MY_NOTE_BASE);
    }

    private void configCard(Card card) {
        card.setPrefWidth(ScreenUtils.getActualSize(820));
        card.setPrefHeight(ScreenUtils.getActualSize(340));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configCard(crdHumanResource);
        configCard(crdWarehouseHome);
        configCard(crdMyNoteCategory);

        applyContentStyle(grdMyNoteCategoryContent, grdHumanResourceContent, grdWarehouseHomeContent);
        this.myNoteCategoryInfo = myNoteCategoryService.getInfo();

        lblTotalMyNoteCategory.textProperty().bindBidirectional(myNoteCategoryInfo.totalCountProperty());
        lblInProgressCount.textProperty().bindBidirectional(myNoteCategoryInfo.inProgressCountProperty());
        lblDoneCount.textProperty().bindBidirectional(myNoteCategoryInfo.doneCountProperty());
        lblStoppedCount.textProperty().bindBidirectional(myNoteCategoryInfo.stoppedCountProperty());


        this.humanResourceInfo = categoryService.getInfo();
        lblPersonCount.textProperty().bindBidirectional(humanResourceInfo.personCountProperty());
        lblCategoryCount.textProperty().bindBidirectional(humanResourceInfo.categoryCountProperty());

        this.warehouseInfo = warehouseService.getInfo();
        this.lblTotalWarehouse.textProperty().bindBidirectional(warehouseInfo.totalWarehouseProperty());
        this.lblLostCount.textProperty().bindBidirectional(warehouseInfo.lostCountProperty());
        this.lblUnknownCount.textProperty().bindBidirectional(warehouseInfo.unknownCountProperty());
        this.lblReceivedCount.textProperty().bindBidirectional(warehouseInfo.receivedCountProperty());
        this.lblRejectedCount.textProperty().bindBidirectional(warehouseInfo.rejectedCountProperty());


        masonry.setHgap(ScreenUtils.getActualSize(22));
        masonry.setVgap(ScreenUtils.getActualSize(22));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10)));
    }

    private void applyContentStyle(GridPane... gridPanes) {
        for (GridPane gridPane : gridPanes) {
            gridPane.setVgap(ScreenUtils.getActualSize(20));
            gridPane.setHgap(ScreenUtils.getActualSize(50));
        }
    }

}
