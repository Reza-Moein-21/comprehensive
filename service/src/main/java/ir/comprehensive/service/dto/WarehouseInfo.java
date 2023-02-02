package ir.comprehensive.service.dto;

public record WarehouseInfo(int totalWarehouse,
                            int lostCount,
                            int unknownCount,
                            int receivedCount,
                            int rejectedCount) {
}
