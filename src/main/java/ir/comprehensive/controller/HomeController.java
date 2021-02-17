package ir.comprehensive.controller;

import com.github.mfathi91.time.PersianDate;
import ir.comprehensive.component.Card;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.component.hadis.HadisComponent;
import ir.comprehensive.entity.HadisEntity;
import ir.comprehensive.fxmodel.HumanResourceInfo;
import ir.comprehensive.fxmodel.MyNoteCategoryInfo;
import ir.comprehensive.fxmodel.WarehouseInfo;
import ir.comprehensive.service.CategoryFxService;
import ir.comprehensive.service.HadisFxService;
import ir.comprehensive.service.MyNoteCategoryFxService;
import ir.comprehensive.service.WarehouseFxService;
import ir.comprehensive.utils.AzanType;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
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
    //    public ImageView imgCalender;
    public ImageView imgTime;
    public TilePane tlpClaAndClock;
    public HBox hbxClock;
    //    public HBox hbxCalender;
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
    public SimpleDatePicker sdpAzan;
    public Label lblTime;
    public Label lblSobh;
    public Label lblTolooAftab;
    public Label lblZohre;
    public Label lblGhroobAftab;
    public Label lblMaghreb;
    public Label lblNemehShab;
    public HadisComponent hadisComponent;
    public Label lblOwghatTitle;
    private WarehouseInfo warehouseInfo = new WarehouseInfo();

    @FXML
    VBox homePage;
    private StartController startController;
    private MyNoteCategoryFxService myNoteCategoryService;
    private CategoryFxService categoryService;
    private WarehouseFxService warehouseService;
    private HadisFxService hadisService;

    @Value("${hadis.change.time}")
    private int hadisChangeTime;
    @Value("${azan.remaining.time}")
    private long azanRemainingTime;

    public HomeController(StartController startController, MyNoteCategoryFxService myNoteCategoryService, CategoryFxService categoryService, WarehouseFxService warehouseService, HadisFxService hadisService) {
        this.startController = startController;
        this.myNoteCategoryService = myNoteCategoryService;
        this.categoryService = categoryService;
        this.warehouseService = warehouseService;
        this.hadisService = hadisService;
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

    private ChangeListener<LocalDate> handelDateChange = (observable, oldValue, newValue) -> {
        try {
            FileInputStream file = new FileInputStream(new File("./.database/owghat.xlsx"));
            PersianDate persianDate = PersianDate.fromGregorian(newValue);

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(persianDate.getMonthValue() - 1);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int rowIndex = row.getRowNum();
                if (rowIndex == persianDate.getDayOfMonth()) {
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        setAzanValue(cell.getColumnIndex(), getCellStringValue(cell));
                    }

                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            clearAzanFields();
        }
    };

    private void clearAzanFields() {
        lblSobh.setText("---");
        lblTolooAftab.setText("---");
        lblZohre.setText("---");
        lblGhroobAftab.setText("---");
        lblMaghreb.setText("---");
        lblNemehShab.setText("---");
    }

    private String getCellStringValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    private void setAzanValue(int index, String value) {
        switch (index) {
            case 1:
                lblSobh.setText(value);
                return;
            case 2:
                lblTolooAftab.setText(value);
                return;
            case 3:
                lblZohre.setText(value);
                return;
            case 4:
                lblGhroobAftab.setText(value);
                return;
            case 5:
                lblMaghreb.setText(value);
                return;
            case 6:
                lblNemehShab.setText(value);
                return;

        }
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

        sdpAzan.valueProperty().addListener(handelDateChange);
        sdpAzan.setValue(LocalDate.now());
        sdpAzan.setDialogContainer(this.startController.mainStack);

        setTimeByTimeLine();
        setHadisByTimeLine();

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

        AnchorPane.setTopAnchor(vbxAzan, ScreenUtils.getActualSize(72));
        vbxAzan.setStyle("-fx-background-color: #42a5f5;-fx-border-width: " + ScreenUtils.getActualSize(2.5) + ";-fx-border-color: #2a4d55;-fx-border-radius: " + ScreenUtils.getActualSize(5) + "; -fx-background-radius: " + ScreenUtils.getActualSize(5));
        vbxAzan.setSpacing(ScreenUtils.getActualSize(5));


        hbxClock.setSpacing(ScreenUtils.getActualSize(10));
        hbxTolooAftab.setSpacing(ScreenUtils.getActualSize(10));
        hbxSobh.setSpacing(ScreenUtils.getActualSize(10));
        hbxZohre.setSpacing(ScreenUtils.getActualSize(10));
        hbxGhroobAftab.setSpacing(ScreenUtils.getActualSize(10));
        hbxMaghreb.setSpacing(ScreenUtils.getActualSize(10));
        hbxNemehShab.setSpacing(ScreenUtils.getActualSize(10));

        imgAzan.setFitHeight(ScreenUtils.getActualSize(170));
        imgAzan.setFitWidth(ScreenUtils.getActualSize(170));
        AnchorPane.setTopAnchor(imgAzan, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(imgAzan, ScreenUtils.getActualSize(10.0));

        lblOwghatTitle.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(40) + "px;-fx-font-family: 'shabnam';");
        AnchorPane.setTopAnchor(lblOwghatTitle, ScreenUtils.getActualSize(0.0));
        AnchorPane.setRightAnchor(lblOwghatTitle, ScreenUtils.getActualSize(200.0));

//        imgCalender.setFitWidth(ScreenUtils.getActualSize(64));
//        imgCalender.setFitHeight(ScreenUtils.getActualSize(64));
        imgTime.setFitWidth(ScreenUtils.getActualSize(64));
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

    private boolean isShowAgainNotify = true;

    private void setTimeByTimeLine() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime now = LocalTime.now();
            lblTime.setText(now.format(formatter));
            LocalTime timeSobh = getTimeFromText(lblSobh.getText());
            LocalTime timeZohre = getTimeFromText(lblZohre.getText());
            LocalTime timeMaghreb = getTimeFromText(lblMaghreb.getText());
            if (timeSobh != null && timeZohre != null && timeMaghreb != null) {
                LocalTime nthNextTime = now.plusMinutes(azanRemainingTime);
                if (isEqualsByHourAndMinute(now, timeSobh)) {
                    if (isShowAgainNotify) {

                        Notify.showMessage("اذان صبح", AzanType.SOBH);
                    }
                    isShowAgainNotify = false;
                } else if (isEqualsByHourAndMinute(nthNextTime, timeSobh)) {
                    if (isShowAgainNotify) {
                        Notify.showMessage("۱۵ دقیقه تا اذان صبح", AzanType.REMINDEING);
                    }
                    isShowAgainNotify = false;
                } else if (isEqualsByHourAndMinute(now, timeZohre)) {
                    if (isShowAgainNotify) {
                        Notify.showMessage("اذان ظهر", AzanType.ZOHRE);
                    }
                    isShowAgainNotify = false;
                } else if (isEqualsByHourAndMinute(nthNextTime, timeZohre)) {
                    if (isShowAgainNotify) {
                        Notify.showMessage("۱۵ دقیقه تا اذان ظهر", AzanType.REMINDEING);
                    }
                    isShowAgainNotify = false;
                } else if (isEqualsByHourAndMinute(now, timeMaghreb)) {
                    if (isShowAgainNotify) {
                        Notify.showMessage("اذان مغرب", AzanType.MAGHREB);
                    }
                    isShowAgainNotify = false;
                } else if (isEqualsByHourAndMinute(nthNextTime, timeMaghreb)) {
                    if (isShowAgainNotify) {
                        Notify.showMessage("۱۵ دقیقه تا اذان مغرب", AzanType.REMINDEING);
                    }
                    isShowAgainNotify = false;
                } else {
                    isShowAgainNotify = true;
                }


            }
        }, new javafx.animation.KeyValue[]{}), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private boolean isEqualsByHourAndMinute(LocalTime now, LocalTime timeSobh) {
        return now.getHour() == timeSobh.getHour() && now.getMinute() == timeSobh.getMinute();
    }

    private LocalTime getTimeFromText(String text) {
        if (text == null) {
            return null;
        }

        return LocalTime.parse(text, DateTimeFormatter.ofPattern("H:mm"));
    }

    private void setHadisByTimeLine() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            HadisEntity randomHadis = hadisService.getRandomHadis();
            if (randomHadis == null) {
                hadisComponent.setTitle("------------");
                hadisComponent.setContent("----------------------------");
            } else {
                hadisComponent.setTitle(randomHadis.getTitle());
                hadisComponent.setContent(randomHadis.getDescription());
            }
        }), new KeyFrame(Duration.minutes(hadisChangeTime)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void applyContentStyle(GridPane... gridPanes) {
        for (GridPane gridPane : gridPanes) {
            gridPane.setVgap(ScreenUtils.getActualSize(20));
            gridPane.setHgap(ScreenUtils.getActualSize(50));
        }
    }

}
