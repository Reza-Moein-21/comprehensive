package ir.comprehensive.service.hr;

import ir.comprehensive.common.factory.ObjectFactory;
import ir.comprehensive.database.factory.CompanyDaoFactory;
import ir.comprehensive.database.factory.PersonDaoFactory;
import ir.comprehensive.service.internal.hr.HumanResourceServiceImpl;

public class HumanResourceServiceFactory implements ObjectFactory<HumanResourceService> {
    private final PersonDaoFactory personDaoFactory = new PersonDaoFactory();
    private final CompanyDaoFactory companyDaoFactory = new CompanyDaoFactory();

    public HumanResourceService getInstance() {
        return new HumanResourceServiceImpl(personDaoFactory.getInstance(), companyDaoFactory.getInstance());
    }
}
