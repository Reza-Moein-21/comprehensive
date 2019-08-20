package ir.comprehensive.component.calenderwidget;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import ir.comprehensive.component.arrowchoiceboxwidget.ArrowChoiceBoxWidget;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

class CalenderHeader extends StackPane {
    public CalenderHeader() {
        this.getChildren().setAll(render());
    }

    private Node render() {
        HBox base = new HBox(ScreenUtils.getActualSize(10));
        Button btnToday = new Button(MessageUtils.Calender.TODAY);
        ArrowChoiceBoxWidget<Integer> acbYear = new ArrowChoiceBoxWidget<>();
        acbYear.valueProperty().bindBidirectional(this.yearProperty());
        acbYear.setPrefWidth(ScreenUtils.getActualSize(360));
        acbYear.setItems(getYearItems());

        ArrowChoiceBoxWidget<PersianMonth> acbMonth = new ArrowChoiceBoxWidget<>();
        acbMonth.valueProperty().bindBidirectional(this.persianMonthProperty());
        acbMonth.setConverter(new StringConverter<PersianMonth>() {
            @Override
            public String toString(PersianMonth object) {
                return object.getPersianName();
            }

            @Override
            public PersianMonth fromString(String string) {
                return null;
            }
        });
        acbMonth.setPrefWidth(ScreenUtils.getActualSize(420));
        acbMonth.setItems(FXCollections.observableArrayList(PersianMonth.values()));

        btnToday.setOnAction(event -> {
            acbMonth.setValue(PersianDate.now().getMonth());
            acbYear.setValue(PersianDate.now().getYear());
        });

        base.setAlignment(Pos.CENTER);
        base.getChildren().setAll(btnToday, acbMonth, acbYear);

        this.setStyle("-fx-border-width: 0 0 " + ScreenUtils.getActualSize(3) + " 0; -fx-border-color: #bdbdbd;-fx-padding: " + ScreenUtils.getActualSize(10) + " 0;");
        return base;
    }

    private ObservableList<Integer> getYearItems() {
        int startYear = 1370;
        ObservableList<Integer> items = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            items.add(startYear);
            startYear++;
        }
        return items;
    }


    /**
     * persianMonth property
     */
    ObjectProperty<PersianMonth> persianMonth = new SimpleObjectProperty<>(this, "persianMonth", PersianDate.now().getMonth());

    public PersianMonth getPersianMonth() {
        return persianMonth.get();
    }

    public ObjectProperty<PersianMonth> persianMonthProperty() {
        return persianMonth;
    }

    public void setPersianMonth(PersianMonth persianMonth) {
        this.persianMonth.set(persianMonth);
    }

    /**
     * year property
     */
    ObjectProperty<Integer> year = new SimpleObjectProperty<>(this, "year", PersianDate.now().getYear());

    public Integer getYear() {
        return year.get();
    }

    public ObjectProperty<Integer> yearProperty() {
        return year;
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }
}
