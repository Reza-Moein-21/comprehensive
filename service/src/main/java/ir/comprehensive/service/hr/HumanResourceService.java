package ir.comprehensive.service.hr;

import ir.comprehensive.database.service.CategoryDao;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.model.dto.HumanResourceInfo;
import ir.comprehensive.service.base.Statistical;

public interface HumanResourceService extends Statistical<HumanResourceInfo> {
    PersonDao getPersonDao();
    CategoryDao getCategoryDao();
}
