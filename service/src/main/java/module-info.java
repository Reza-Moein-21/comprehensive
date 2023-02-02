module ir.comprehensive.service {
    exports ir.comprehensive.service;
    exports ir.comprehensive.service.base;
    exports ir.comprehensive.service.dto;

    requires ir.comprehensive.model;
    requires ir.comprehensive.database;
    requires spring.boot.autoconfigure;
    requires lombok;
    requires spring.context;
}