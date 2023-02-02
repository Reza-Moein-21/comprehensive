package ir.comprehensive.reporting.mapper;

import com.github.mfathi91.time.PersianDate;
import ir.comprehensive.domain.model.ProductDeliveryModel;
import ir.comprehensive.reporting.model.ProductDeliveryReportBean;
import org.springframework.stereotype.Component;

@Component
public class ProductDeliveryDetailReportMapper implements BaseReportMapper<ProductDeliveryModel, ProductDeliveryReportBean.ProductDeliveryDetailReport, Long> {


    @Override
    public ProductDeliveryReportBean.ProductDeliveryDetailReport modelToReportBean(ProductDeliveryModel model) {
        ProductDeliveryReportBean.ProductDeliveryDetailReport detailReport = new ProductDeliveryReportBean.ProductDeliveryDetailReport();
        detailReport.setCount(String.valueOf(model.getCount()));
        detailReport.setDeliveryDate(PersianDate.fromGregorian(model.getDeliveryDate()).toString());
        detailReport.setReceivedDate(model.getReceivedDate() == null ? "-" : PersianDate.fromGregorian(model.getReceivedDate()).toString());
        detailReport.setFullName(model.getPerson() == null ? "" : model.getPerson().getFirstName() + " " + model.getPerson().getLastName());
        detailReport.setStatus(model.getStatus().toString());
        if (model.getProduct() == null) {
            detailReport.setProductName("");
            detailReport.setProductCode("");
            detailReport.setCategory("");
        } else {
            detailReport.setProductName(model.getProduct().getTitle());
            detailReport.setProductCode(model.getProduct().getCode());
            detailReport.setCategory(model.getProduct().getCategory() == null ? "" : model.getProduct().getCategory().getTitle());
        }

        return detailReport;
    }

    @Override
    public ProductDeliveryModel reportBeanToModel(ProductDeliveryReportBean.ProductDeliveryDetailReport dto) {
        return null;
    }
}
