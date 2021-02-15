package ir.comprehensive.fxmodel;

import ir.comprehensive.fxmodel.basemodel.BaseReportBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductDeliveryReportBean {
    private Map<String, Object> params;
    private List<ProductDeliveryDetailReport> tableDetail;

    @Getter
    @Setter
    public static class ProductDeliveryDetailReport extends BaseReportBean {
        private String productName;
        private String productCode;
        private String category;
        private String fullName;
        private String count;
        private String status;
        private String deliveryDate;
        private String receivedDate;

    }

}
