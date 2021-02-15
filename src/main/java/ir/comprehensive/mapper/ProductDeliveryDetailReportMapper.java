package ir.comprehensive.mapper;

import com.github.mfathi91.time.PersianDate;
import ir.comprehensive.entity.ProductDelivery;
import ir.comprehensive.model.ProductDeliveryReportBean;
import org.springframework.stereotype.Component;

@Component
public class ProductDeliveryDetailReportMapper implements BaseMapper<ProductDelivery, ProductDeliveryReportBean.ProductDeliveryDetailReport> {


    @Override
    public ProductDeliveryReportBean.ProductDeliveryDetailReport entityToModel(ProductDelivery entity) {
        ProductDeliveryReportBean.ProductDeliveryDetailReport model = new ProductDeliveryReportBean.ProductDeliveryDetailReport();
        model.setCount(String.valueOf(entity.getCount()));
        model.setDeliveryDate(PersianDate.fromGregorian(entity.getDeliveryDate()).toString());
        model.setReceivedDate(entity.getReceivedDate() == null ? "-" : PersianDate.fromGregorian(entity.getReceivedDate()).toString());
        model.setFullName(entity.getPerson() == null ? "" : entity.getPerson().getFirstName() + " " + entity.getPerson().getLastName());
        model.setStatus(entity.getStatus().toString());
        if (entity.getProduct() == null) {
            model.setProductName("");
            model.setProductCode("");
            model.setCategory("");
        } else {
            model.setProductName(entity.getProduct().getTitle());
            model.setProductCode(entity.getProduct().getCode());
            model.setCategory(entity.getProduct().getCategory() == null ? "" : entity.getProduct().getCategory().getTitle());
        }

        return model;
    }

    @Override
    public ProductDelivery modelToEntity(ProductDeliveryReportBean.ProductDeliveryDetailReport dto) {
        return null;
    }
}
