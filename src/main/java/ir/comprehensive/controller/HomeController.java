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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    public HBox hbxHadis;
    public ImageView imgCalender;
    public ImageView imgTime;
    public TilePane tlpClaAndClock;
    public HBox hbxClock;
    public HBox hbxCalender;
    public HBox hbxTolooAftab;
    public ImageView imgSobh;
    public HBox hbxSobh;
    public HBox hbxZohre;
    public ImageView imgZohre;
    public ImageView imgTolooAftab;
    public HBox hbxGhroobAftab;
    public ImageView imgGhroobAftab;
    public HBox hbxMaghreb;
    public ImageView imgMaghreb;
    public HBox hbxNemehShab;
    public ImageView imgNemehShab;
    public VBox vbxAzan;
    public AnchorPane ancAzan;
    public HBox hbxAzan;
    public GridPane grdAzan;
    public ImageView imgAzan;
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


        hbxHadis.setMaxHeight(ScreenUtils.getActualSize(250));


        hbxAzan.setMaxHeight(ScreenUtils.getActualSize(410));
        ancAzan.setMinWidth(ScreenUtils.getActualSize(1100));

        AnchorPane.setTopAnchor(vbxAzan, ScreenUtils.getActualSize(68));
        vbxAzan.setStyle("-fx-background-color: #80DEEA;-fx-border-width: " + ScreenUtils.getActualSize(0.5) + ";-fx-border-color: #d1d1d1;-fx-border-radius: " + ScreenUtils.getActualSize(5) + "; -fx-background-radius: " + ScreenUtils.getActualSize(5));
        vbxAzan.setSpacing(ScreenUtils.getActualSize(5));


        hbxClock.setSpacing(ScreenUtils.getActualSize(10));
        hbxCalender.setSpacing(ScreenUtils.getActualSize(10));
        hbxTolooAftab.setSpacing(ScreenUtils.getActualSize(10));
        hbxSobh.setSpacing(ScreenUtils.getActualSize(10));
        hbxZohre.setSpacing(ScreenUtils.getActualSize(10));
        hbxGhroobAftab.setSpacing(ScreenUtils.getActualSize(10));
        hbxMaghreb.setSpacing(ScreenUtils.getActualSize(10));
        hbxNemehShab.setSpacing(ScreenUtils.getActualSize(10));

        imgAzan.setFitHeight(ScreenUtils.getActualSize(170));
        imgAzan.setFitWidth(ScreenUtils.getActualSize(170));
        AnchorPane.setTopAnchor(imgAzan, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(imgAzan, ScreenUtils.getActualSize(40.0));

        imgCalender.setFitWidth(ScreenUtils.getActualSize(64));
        imgTime.setFitWidth(ScreenUtils.getActualSize(64));
        imgCalender.setFitHeight(ScreenUtils.getActualSize(64));
        imgTime.setFitHeight(ScreenUtils.getActualSize(64));

        tlpClaAndClock.setHgap(ScreenUtils.getActualSize(10));
        tlpClaAndClock.setVgap(ScreenUtils.getActualSize(10));
        tlpClaAndClock.setStyle("-fx-padding: " + ScreenUtils.getActualSize(20) + " 0px " + ScreenUtils.getActualSize(40) + " 0px;");

        grdAzan.setHgap(ScreenUtils.getActualSize(50));
        grdAzan.setVgap(ScreenUtils.getActualSize(50));


        imgSobh.setFitWidth(ScreenUtils.getActualSize(64));
        imgSobh.setFitHeight(ScreenUtils.getActualSize(64));

        imgZohre.setFitWidth(ScreenUtils.getActualSize(64));
        imgZohre.setFitHeight(ScreenUtils.getActualSize(64));


        imgGhroobAftab.setFitWidth(ScreenUtils.getActualSize(64));
        imgMaghreb.setFitHeight(ScreenUtils.getActualSize(64));
        imgNemehShab.setFitWidth(ScreenUtils.getActualSize(64));
        imgNemehShab.setFitHeight(ScreenUtils.getActualSize(64));


        imgTolooAftab.setFitWidth(ScreenUtils.getActualSize(64));
        imgTolooAftab.setFitHeight(ScreenUtils.getActualSize(64));


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
