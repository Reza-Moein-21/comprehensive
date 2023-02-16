package ir.comprehensive.database.model;

import ir.comprehensive.domain.model.base.DomainModel;

import java.util.Collections;
import java.util.List;

public record PageModel<M extends DomainModel<?>>(
        int currentPage,
        int totalItems,
        int totalPages,
        int numberOfElements,
        List<M> content) {
    public static <M extends DomainModel<?>> PageModel<M> ofEmpty() {
        return new PageModel<>(0, 0, 0, 0, Collections.emptyList());
    }

    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
}
