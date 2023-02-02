open module ir.comprehensive.database {
    requires ir.comprehensive.model;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires java.sql;
    requires h2;
    requires org.jooq;
    requires lombok;
    requires com.zaxxer.hikari;
    requires org.mapstruct;

    exports ir.comprehensive.database.service;
    exports ir.comprehensive.database.service.base;
    exports ir.comprehensive.database.exception;
    exports ir.comprehensive.database.model;


}