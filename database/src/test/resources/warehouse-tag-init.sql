delete
from WAREHOUSE_WAREHOUSE_TAG
where 1 = 1;

delete
from WAREHOUSE
where 1 = 1;

delete
from WAREHOUSE_TAG
where 1 = 1;



insert into warehouse (id, code, company_name, count, title)
values(112 ,'RX1' ,'Megatesla' ,100 ,'Capacitor');

insert into warehouse (id, code, company_name, count, title)
values(113 ,'RX2' ,'Megatesla' ,100 ,'Capacitor l2');

insert into warehouse (id, code, company_name, count, title)
values(114 ,'C3pO' ,'Megatesla' ,100 ,'Capacitor Robot');

insert into warehouse (id, code, company_name, count, title)
values(115 ,'R1' ,'Megatesla' ,100 ,'Resistor');

insert into warehouse_tag (id, title) values(1,'GOOD');
insert into warehouse_tag (id, title) values(2,'BAD');

insert into WAREHOUSE_WAREHOUSE_TAG(warehouse_id, tag_id) values(112, 1);
insert into WAREHOUSE_WAREHOUSE_TAG(warehouse_id, tag_id) values(113, 1);
insert into WAREHOUSE_WAREHOUSE_TAG(warehouse_id, tag_id) values(114, 1);
insert into WAREHOUSE_WAREHOUSE_TAG(warehouse_id, tag_id) values(115, 2);