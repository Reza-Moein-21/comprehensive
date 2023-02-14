package ir.comprehensive.domain.model.dto;

public record WarehouseInfo(int totalWarehouse,
                            int lostCount,
                            int unknownCount,
                            int receivedCount,
                            int rejectedCount) {
}
