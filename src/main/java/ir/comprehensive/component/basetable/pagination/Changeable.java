package ir.comprehensive.component.basetable.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface Changeable {
    Page paginationChange(PageRequest pageRequest);
}
