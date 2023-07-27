package ir.comprehensive.service.internal.hr;

import ir.comprehensive.database.dao.CompanyDao;
import ir.comprehensive.database.dao.PersonDao;
import ir.comprehensive.domain.model.dto.HumanResourceInfo;
import ir.comprehensive.service.hr.HumanResourceService;

public class HumanResourceServiceImpl implements HumanResourceService {
    private final PersonDao personDao;
    private final CompanyDao companyDao;

    public HumanResourceServiceImpl(PersonDao personDao, CompanyDao companyDao) {
        this.personDao = personDao;
        this.companyDao = companyDao;
    }

    @Override
    public HumanResourceInfo getInfo() {
        return new HumanResourceInfo(personDao.totalCount(), companyDao.totalCount());
    }

    @Override
    public PersonDao personDao() {
        return this.personDao;
    }

    @Override
    public CompanyDao companyDao() {
        return this.companyDao;
    }
}
