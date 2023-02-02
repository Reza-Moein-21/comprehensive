package ir.comprehensive.jfxapp.component.basetable.pagination;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import ir.comprehensive.jfxapp.utils.MessageUtils;
import ir.comprehensive.jfxapp.utils.ScreenUtils;
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
    private static final String ALL_ITEMS = "همه موارد";

    private PaginationModel paginationModel = new PaginationModel();
    private PageRequest pageRequest;
    private Changeable onPaginationChange;

    private JFXButton btnNext = new JFXButton();
    private JFXButton btnPrevious = new JFXButton();
    private JFXButton btnFirst = new JFXButton();
    private JFXButton btnEnd = new JFXButton();
    private Label lblPageNumber = new Label("");
    private final JFXComboBox<String> cmbPageCount;

    {
        cmbPageCount = new JFXComboBox<>(FXCollections.observableArrayList(Stream.of("10", "20", "50", ALL_ITEMS).collect(Collectors.toList())));
    }

    public TablePagination() {
        pageRequest = PageRequest.of(DEFAULT_START_PAGE, DEFAULT_PAGE_SIZE);
        btnNext.getStyleClass().addAll("table-row-button", "next-table-row-button");
        btnNext.setPrefWidth(ScreenUtils.getActualSize(38));
        btnNext.setPrefHeight(ScreenUtils.getActualSize(38));

        btnPrevious.getStyleClass().addAll("table-row-button", "previous-table-row-button");
        btnPrevious.setPrefWidth(ScreenUtils.getActualSize(38));
        btnPrevious.setPrefHeight(ScreenUtils.getActualSize(38));

        btnFirst.getStyleClass().addAll("table-row-button", "first-table-row-button");
        btnFirst.setPrefWidth(ScreenUtils.getActualSize(38));
        btnFirst.setPrefHeight(ScreenUtils.getActualSize(38));

        btnEnd.getStyleClass().addAll("table-row-button", "end-table-row-button");
        btnEnd.setPrefWidth(ScreenUtils.getActualSize(38));
        btnEnd.setPrefHeight(ScreenUtils.getActualSize(38));


        btnNext.setOnAction(event -> {
            Integer nextPage = paginationModel.getCurrentPage() + 1;
            pageRequest = PageRequest.of(nextPage, getPageCountNumber(cmbPageCount));
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));
        });

        btnPrevious.setOnAction(event -> {
            Integer previousPage = paginationModel.getCurrentPage() - 1;
            pageRequest = PageRequest.of(previousPage, getPageCountNumber(cmbPageCount));
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));

        });

        btnFirst.setOnAction(event -> {
            pageRequest = PageRequest.of(DEFAULT_START_PAGE, getPageCountNumber(cmbPageCount));
            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));
        });

        btnEnd.setOnAction(event -> {
            pageRequest = PageRequest.of(paginationModel.getTotalPages() - 1, getPageCountNumber(cmbPageCount));
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
            if (newValue.equals(ALL_ITEMS)) {
                pageRequest = PageRequest.of(currentPage, Integer.MAX_VALUE);
            } else {
                pageRequest = PageRequest.of(currentPage, Integer.parseInt(newValue));
            }

            Page page = onPaginationChange.paginationChange(pageRequest);
            update(new PaginationModelAdapter(page));
        });

        cmbPageCount.setValue(DEFAULT_PAGE_SIZE.toString());
        this.setSpacing(ScreenUtils.getActualSize(100));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(new CenterHBox(btnEnd, btnNext, lblPageNumber, btnPrevious, btnFirst), new CenterHBox(new Label(MessageUtils.Message.COUNT_PER_PAGE), cmbPageCount));
    }

    private int getPageCountNumber(JFXComboBox<String> cmbPageCount) {
        return Integer.parseInt(cmbPageCount.getValue());
    }

    @Override
    public void update(PaginationModelAdapter modelAdapter) {
        paginationModel = modelAdapter;

        int fromNumber;
        long toNumber;
        long totalNumber = paginationModel.getTotalItems();
        if (cmbPageCount.getValue().equals(ALL_ITEMS)) {
            fromNumber = 1;
            toNumber = totalNumber;
        } else {
            fromNumber = 1 + (getPageCountNumber(cmbPageCount) * (paginationModel.getCurrentPage()));
            toNumber = getPageCountNumber(cmbPageCount) * (paginationModel.getCurrentPage() + 1L);
        }


        if (toNumber > totalNumber) {
            toNumber = totalNumber;
        }

        lblPageNumber.setText(String.format("%d-%d از %d", toNumber, fromNumber, totalNumber));

        btnNext.setDisable(false);
        btnPrevious.setDisable(false);
        btnFirst.setDisable(false);
        btnEnd.setDisable(false);

        if (paginationModel.getNumberOfElements() == 0 || paginationModel.getTotalPages() == 1) {
            btnNext.setDisable(true);
            btnPrevious.setDisable(true);
            btnFirst.setDisable(true);
            btnEnd.setDisable(true);
            return;
        }

        if (paginationModel.getCurrentPage() == 0) {
            btnPrevious.setDisable(true);
            btnFirst.setDisable(true);
            return;
        }

        if (paginationModel.getCurrentPage() == paginationModel.getTotalPages() - 1) {
            btnNext.setDisable(true);
            btnEnd.setDisable(true);
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

