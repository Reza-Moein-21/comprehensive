package ir.comprehensive.service.hr;

import ir.comprehensive.database.dao.CompanyDao;
import ir.comprehensive.database.dao.PersonDao;
import ir.comprehensive.domain.model.dto.HumanResourceInfo;
import ir.comprehensive.service.base.Statistical;


public interface HumanResourceService extends Statistical<HumanResourceInfo> {
    PersonDao personDao();
    CompanyDao companyDao();
}
