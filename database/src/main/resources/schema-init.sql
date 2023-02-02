delete
from WAREHOUSE_WAREHOUSE_TAG
where 1 = 1;

delete
from WAREHOUSE
where 1 = 1;

delete
from MY_NOTE
where 1 = 1;


delete
from PRODUCT_DELIVERY
where 1 = 1;

delete
from PERSON_CATEGORY
where 1 = 1;
delete
from PERSON
where 1 = 1;
delete
from CATEGORY
where 1 = 1;

delete
FROM WAREHOUSE_CATEGORY
where 1 = 1;

insert into PERSON
values (987654321, '', 'alializadeh@gmail.com', 'علی', 'علی زاده', '09118445768');
insert into PERSON
values (987654320, '', 'beh23@yahoo.com', 'بهنام', 'جعفری', '09324433534');
insert into PERSON
values (987654319, '', 'tt@gmail.com', 'تقی', 'تقی زاده', '09333454568');
insert into PERSON
values (987654221, '', 'hashem@gmail.com', 'جمشید', 'هاشمی', '09134543527');
insert into PERSON
values (987612345, '', 'roozK@hotmail.com', 'زوربه', 'کرجی', '09347783774');
insert into PERSON
values (678954321, '', 'haditafreshi@gmail.com', 'هادی', 'تفرشی', '09114552266');
insert into PERSON
values (985790321, '', 'hesam@gmail.com', 'حسام', 'ناصری', '09987334565');
insert into PERSON
values (883322556, '', 'mahmoodahmaad@yahoo.com', 'محمود', 'احمدزاده', '09133455643');
insert into PERSON
values (987654332, '', 'akbarakbar@gmail.com', 'اکبر', 'اکبری', '09334546323');
insert into PERSON
values (934543526, '', 'hashem@yahoo.com', 'هاشم', 'راهدار', '09368749834');


insert into CATEGORY
values (987234593, 'کرج-جاده مخصوص', 'شرکت تجاری بزرگ سامان', 'samaan@gmail.com', '875-23424', '02876654554',
        'سامان کیش');
insert into CATEGORY
values (987234764, 'تهران-میرداماد-نبش میدان مادر', 'شرکت به پویان', 'behh@gmail.com', '875-4435', '02177657643',
        'شرکت به پویان');
insert into CATEGORY
values (934434533, 'کرج-جاده مخصوص', 'شرکت تجاری البرز', 'alborze@gmail.com', '223-2234', '02876652233', 'البرز');
insert into CATEGORY
values (997234555, 'تهران - سعادت آباد', 'شرکت تجاری سعادت', 'sm@gmail.com', '098-23423', '02876633443', 'سعادت');


insert into PERSON_CATEGORY
values (987654321, 987234593);
insert into PERSON_CATEGORY
values (985790321, 987234593);
insert into PERSON_CATEGORY
values (987612345, 934434533);
insert into PERSON_CATEGORY
values (934543526, 997234555);
insert into PERSON_CATEGORY
values (987654332, 987234764);

insert into WAREHOUSE_CATEGORY
values (8793452, 'دست افزار');
insert into WAREHOUSE_CATEGORY
values (98342565, 'الکترونیک');
insert into WAREHOUSE_CATEGORY
values (79438294, 'متفرقه')

