package ir.comprehensive.api.fx.base.paging;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FxSortType {
    ASC(Sort.Direction.ASC), DESC(Sort.Direction.DESC);

    private final Sort.Direction springSortDirection;

    public Sort.Direction toSpringSortDirection() {
        return springSortDirection;
    }
}
