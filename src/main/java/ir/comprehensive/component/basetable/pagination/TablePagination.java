package ir.comprehensive.component.basetable.pagination;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TablePagination extends HBox implements Updater {
    public static final Integer DEFAULT_START_PAGE = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    private PaginationModel paginationModel = new PaginationModel();
    private PageRequest pageRequest;
    private Changeable onPaginationChange;

    private JFXButton btnNext = new JFXButton(">");
    private JFXButton btnPrevious = new JFXButton("<");
    private Label lblPageNumber = new Label("");
    private JFXComboBox<Integer> cmbPageCount = new JFXComboBox<>(FXCollections.observableArrayList(Stream.of(10, 20, 50).collect(Collectors.toList())));

    public TablePagination() {
        pageRequest = PageRequest.of(DEFAULT_START_PAGE, DEFAULT_PAGE_SIZE);
        btnNext.setButtonType(JFXButton.ButtonType.RAISED);
        btnPrevious.setButtonType(JFXButton.ButtonType.RAISED);


        btnNext.setOnAction(event -> {
            Integer nextPage = paginationModel.getCurrentPage() + 1;
            pageRequest = PageRequest.of(nextPage, cmbPageCount.getValue());
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));
        });

        btnPrevious.setOnAction(event -> {
            Integer previousPage = paginationModel.getCurrentPage() - 1;
            pageRequest = PageRequest.of(previousPage, cmbPageCount.getValue());
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));

        });

        cmbPageCount.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (onPaginationChange == null) {
                return;
            }
            // reset pagination model
            paginationModel = new PaginationModel();

            Integer currentPage = paginationModel.getCurrentPage();
            pageRequest = PageRequest.of(currentPage, newValue);
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));
        });

        cmbPageCount.setValue(DEFAULT_PAGE_SIZE);
        this.setSpacing(ScreenUtils.getActualSize(100));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(new CenterHBox(btnNext, lblPageNumber, btnPrevious), new CenterHBox(new Label(MessageUtils.Message.COUNT_PER_PAGE), cmbPageCount));
    }

    @Override
    public void update(PaginationModelAdapter modelAdapter) {
        paginationModel = modelAdapter;

        int fromNumber = 1 + (cmbPageCount.getValue() * (paginationModel.getCurrentPage()));
        long toNumber = cmbPageCount.getValue() * (paginationModel.getCurrentPage() + 1);

        long totalNumber = paginationModel.getTotalItems();

        if (toNumber > totalNumber) {
            toNumber = totalNumber;
        }

        lblPageNumber.setText(String.format("%d-%d از %d", toNumber, fromNumber, totalNumber));

        btnNext.setDisable(false);
        btnPrevious.setDisable(false);

        if (paginationModel.getNumberOfElements() == 0 || paginationModel.getTotalPages() == 1) {
            btnNext.setDisable(true);
            btnPrevious.setDisable(true);
            return;
        }

        if (paginationModel.getCurrentPage() == 0) {
            btnPrevious.setDisable(true);
            return;
        }

        if (paginationModel.getCurrentPage() == paginationModel.getTotalPages() - 1) {
            btnNext.setDisable(true);
            return;
        }
    }


    private class CenterHBox extends HBox {
        {
            this.setSpacing(ScreenUtils.getActualSize(10));
            this.setAlignment(Pos.CENTER);
        }

        public CenterHBox() {
        }

        public CenterHBox(double spacing) {
            super(spacing);
        }

        public CenterHBox(Node... children) {
            super(children);
        }

        public CenterHBox(double spacing, Node... children) {
            super(spacing, children);
        }
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setOnPaginationChange(Changeable onPaginationChange) {
        this.onPaginationChange = onPaginationChange;
    }
}

