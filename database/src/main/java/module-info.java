open module ir.comprehensive.database {
    requires ir.comprehensive.model;
    requires java.sql;
    requires h2;
    requires org.jooq;
    requires com.zaxxer.hikari;
    requires org.mapstruct;
    requires org.slf4j;
    requires org.apache.commons.lang3;

    exports ir.comprehensive.database.service;
    exports ir.comprehensive.database.service.base;
    exports ir.comprehensive.database.exception;
    exports ir.comprehensive.database.model;
    exports ir.comprehensive.database.factory;


}