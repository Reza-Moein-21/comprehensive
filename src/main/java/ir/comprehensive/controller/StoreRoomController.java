package ir.comprehensive.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ir.comprehensive.component.SimpleDatePicker;
import ir.comprehensive.domain.Person;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static ir.comprehensive.utils.MessageUtils.getMessage;
import static ir.comprehensive.utils.MessageUtils.getMessageBundle;

@Controller
public class StoreRoomController implements Initializable {

    public JFXTextField txfProductName;
    public SimpleDatePicker sdpDeliveryDate;
    public SimpleDatePicker sdpDesiredDate;
    public JFXTextField txfDescription;
    public JFXComboBox<Person> cbxPerson;

    public JFXTextField txfPersonName;


    private ConfigurableApplicationContext context;
    private ProductDeliveryService productDeliveryService;
    private PersonService personService;

    public StackPane con;
    private SimpleDatePicker deliveryDateField;
    public JFXComboBox com;

    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, LocalDate> deliveryDateColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, Person> personColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> productNameColumn;
    @FXML
    public JFXTreeTableView<ProductDeliveryModel> productDeliveryTable;


    public StoreRoomController(ProductDeliveryService productDeliveryService, ConfigurableApplicationContext context, PersonService personService) {
        this.productDeliveryService = productDeliveryService;
        this.context = context;
        this.personService = personService;
    }

    private void fillPersonItems() {
        ObservableList<Person> allPerson = personService.getAll();
        cbxPerson.setItems(allPerson);
    }

    private void fillDataTable() {

        Task<TreeItem<ProductDeliveryModel>> task = new Task<TreeItem<ProductDeliveryModel>>() {
            @Override
            protected TreeItem<ProductDeliveryModel> call() {
                ObservableList<ProductDeliveryModel> allModel = productDeliveryService.getAllModel();
                return new RecursiveTreeItem<>(allModel, RecursiveTreeObject::getChildren);
            }
        };
        task.setOnSucceeded(event -> {
            TreeItem<ProductDeliveryModel> root = task.getValue();
            productNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> param.getValue().getValue().productNameProperty());

            personColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, Person> param) -> param.getValue().getValue().personProperty());

            deliveryDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, LocalDate> param) -> param.getValue().getValue().deliveryDateProperty());

            productDeliveryTable.setRoot(root);
            productDeliveryTable.setShowRoot(false);
            productDeliveryTable.setEditable(false);
        });
        Executors.newCachedThreadPool().execute(task);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillDataTable();


    }


    public void search(ActionEvent actionEvent) {
        System.out.println(deliveryDateField.getPersianDate());
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/storeRoomCreate.fxml"), getMessageBundle(), null, context::getBean);
        fillPersonItems();

        Stage createStage = new Stage();
        Scene createScene = new Scene(parent, 1024, 720);

        createStage.setScene(createScene);
        createStage.setResizable(false);
        createStage.initModality(Modality.WINDOW_MODAL);
        JFXButton button = (JFXButton) actionEvent.getSource();
        createStage.initOwner(button.getScene().getWindow());
        createStage.setTitle(getMessage("create") + " " + getMessage("product"));
        createStage.showAndWait();
        fillDataTable();

    }

    public void save(ActionEvent actionEvent) {
        ProductDeliveryModel model = new ProductDeliveryModel();
        model.setProductName(txfProductName.getText());
        model.setPerson(cbxPerson.getValue());
        model.setDeliveryDate(sdpDeliveryDate.getPersianDate().toGregorian());
        model.setDesiredDate(sdpDesiredDate.getPersianDate().toGregorian());
        model.setDescription(txfDescription.getText());
        productDeliveryService.save(model);
        getStage(actionEvent).close();
    }

    private Stage getStage(Event source) {
        Node n = (Node) source.getSource();
        return (Stage) n.getScene().getWindow();
    }
}
