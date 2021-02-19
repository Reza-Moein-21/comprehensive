package ir.comprehensive.api.fx.base.paging;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxPagingResponse<T> {

    /**
     * Number of item in current page.
     */
    private int currentPageCount;

    /**
     * Number of current page. started by 1
     */
    private int currentPageNumber;

    /**
     * Count of query result.
     */
    private int totalCount;

    /**
     * Number of page's.
     */
    private int totalPageCount;

    /**
     * Collection of result for each page.
     */
    @Builder.Default
    private ObservableList<T> content = FXCollections.emptyObservableList();

}
