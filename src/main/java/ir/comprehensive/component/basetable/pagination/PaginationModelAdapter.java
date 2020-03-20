package ir.comprehensive.component.basetable.pagination;

import org.springframework.data.domain.Page;

public class PaginationModelAdapter extends PaginationModel {

    private final Page page;

    public PaginationModelAdapter(Page page) {
        this.page = page;
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }

    @Override
    public int getCurrentPage() {
        return page.getNumber();
    }

    @Override
    public long getTotalItems() {
        return page.getTotalElements();
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }


}
