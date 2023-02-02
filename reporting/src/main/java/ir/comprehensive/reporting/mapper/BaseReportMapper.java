package ir.comprehensive.reporting.mapper;


import ir.comprehensive.domain.model.base.DomainModel;
import ir.comprehensive.reporting.model.BaseReportBean;

import java.io.Serializable;

public interface BaseReportMapper<M extends DomainModel<I>, R extends BaseReportBean, I extends Serializable> {
    R modelToReportBean(M model);

    M reportBeanToModel(R reportBean);
}
