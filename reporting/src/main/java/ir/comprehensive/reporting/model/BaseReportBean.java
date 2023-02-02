package ir.comprehensive.reporting.model;

import java.util.List;

public abstract class BaseReportBean {
    private String rowNumber;

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static  <T extends BaseReportBean> List<T> fillRowNumber(List<T> reportBean) {
        for (T t : reportBean) {
            t.setRowNumber(String.valueOf(reportBean.indexOf(t) + 1));
        }
        return reportBean;
    }
}
