create sequence hibernate_sequence start with 1 increment by 1;
create table category (id bigint not null, address varchar(255), description varchar(255), email varchar(255), fax varchar(255), phone_number varchar(255), title varchar(255) not null, primary key (id));
create table hadis (id bigint not null, description varchar(255), title varchar(255) not null, primary key (id));
create table my_note (id bigint not null, creation_date date not null, description varchar(2147483647), in_activation_date date, is_active boolean not null, priority double not null, title varchar(255) not null, my_note_category_id bigint not null, person_id bigint not null, primary key (id));
create table my_note_category (id bigint not null, description varchar(255), status varchar(20), title varchar(255) not null, primary key (id));
create table my_note_temp (id bigint not null, my_note_id bigint, primary key (id));
create table person (id bigint not null, description varchar(2147483647), email varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, phone_number varchar(255), primary key (id));
create table person_category (person_id bigint not null, category_id bigint not null, primary key (person_id, category_id));
create table product (id bigint not null, title varchar(255) not null, primary key (id));
create table product_delivery (id bigint not null, count bigint, delivery_date date not null, description varchar(255), desired_date date, received_date date, status varchar(20), person_id bigint not null, product_id bigint not null, primary key (id));
create table warehouse (id bigint not null, code varchar(255) not null, company_name varchar(255), count bigint not null, description varchar(255), producer_name varchar(255), title varchar(255) not null, warehouse_category_id bigint, primary key (id));
create table warehouse_category (id bigint not null, title varchar(255) not null, primary key (id));
create table warehouse_tag (id bigint not null, title varchar(255) not null, primary key (id));
create table warehouse_warehouse_tag (warehouse_id bigint not null, tag_id bigint not null);
alter table category add constraint UK_lnmf77qvjnr2lmyxrrydom9hd unique (title);
alter table person add constraint UKapiro0qew7es0dcavk0ia8wp4 unique (first_name, last_name);
alter table my_note add constraint FK_MY_NOTE_MY_NOTE_CATEGORY foreign key (my_note_category_id) references my_note_category;
alter table my_note add constraint FK_MY_NOTE_PERSON foreign key (person_id) references person;
alter table my_note_temp add constraint FK_MY_NOTE_MY_NOTE_TEMP foreign key (my_note_id) references my_note;
alter table person_category add constraint FKltm4mxb6nywgbedxj2k13yxu2 foreign key (category_id) references category;
alter table person_category add constraint FKhkp679xbxaotqavwtg96j9moq foreign key (person_id) references person;
alter table product_delivery add constraint FK_PRODUCT_DELIVERY_PERSON foreign key (person_id) references person;
alter table product_delivery add constraint FK_PRODUCT_DELIVERY_PRODUCT foreign key (product_id) references warehouse;
alter table warehouse add constraint FK_WHOUSE_WHOUSE_CAT foreign key (warehouse_category_id) references warehouse_category;
alter table warehouse_warehouse_tag add constraint FK_TAG_W foreign key (tag_id) references warehouse_tag;
alter table warehouse_warehouse_tag add constraint FK_W_TAG foreign key (warehouse_id) references warehouse;
