package ir.comprehensive.model;

public class ProductDeliveryReportBean {
    private String productName;
    private String fullName;
    private String count;
    private String status;

    public ProductDeliveryReportBean() {
    }

    public ProductDeliveryReportBean(String productName, String fullName, String count, String status) {
        this.productName = productName;
        this.fullName = fullName;
        this.count = count;
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
