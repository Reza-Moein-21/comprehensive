module ir.comprehensive.service {
    exports ir.comprehensive.service;
    exports ir.comprehensive.service.base;
    exports ir.comprehensive.service.hr;
    exports ir.comprehensive.service.project;
    exports ir.comprehensive.service.warehousing;

    requires ir.comprehensive.model;
    requires ir.comprehensive.database;
    requires lombok;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires org.mapstruct;
    requires org.slf4j;
    requires org.apache.commons.lang3;
}