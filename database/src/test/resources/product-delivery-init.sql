delete
from PERSON
where 1 = 1;

insert into PERSON
values (987654321, '', 'alializadeh@gmail.com', 'علی', 'علی زاده', '09118445768');

insert into WAREHOUSE (id, code, company_name, count, description, producer_name, title)
values(112, 'RX2', 'BlueTec', 19, 'new capacitor', 'john JK', 'RX2 Capacitor');

insert into WAREHOUSE (id, code, company_name, count, description, producer_name, title)
values(113, 'CU1', 'BlueTec', 10, 'resistor', 'john JK', 'Resistor');

insert into PRODUCT_DELIVERY(id, count, delivery_date, description, status, person_id, product_id)
values (887766, 100, DATE '2028-02-01', 'Delivery of some goods', 'UNKNOWN', 987654321, 112);

insert into PRODUCT_DELIVERY(id, count, delivery_date, description, status, person_id, product_id)
values (887767, 100, DATE '2024-02-01', 'Delivery of some goods', 'RECEIVED', 987654321, 113);
