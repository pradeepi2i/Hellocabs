create table payment(id int primary key auto_increment, payment_mode varchar(50), payment_status varchar(50));

create table location(id int primary key auto_increment, landmark varchar(50), location_name varchar(50) unique, address_type varchar(10), postal_code int, is_deleted boolean default false, created_by varchar(30), created_date timestamp, last_modified_by varchar(30), last_modified_date timestamp);

create table cab_category(id int primary key auto_increment, cab_type varchar(30), initial_fare double, extra_fare_per_hour double, peak_hour_fare double, is_deleted boolean default false, created_by varchar(30), created_date timestamp, last_modified_by varchar(30), last_modified_date timestamp);

CREATE TABLE customer (id int(11) NOT NULL AUTO_INCREMENT, first_name varchar(30),last_name varchar(30), mobile_number bigint(20) unique, email varchar(20) unique, password varchar(255) , is_deleted boolean DEFAULT false,  PRIMARY KEY (id), created_by varchar(30), created_date timestamp, last_modified_by varchar(30), last_modified_date timestamp);

create table ride(id int primary key auto_increment, pickup_location int, drop_location int, ride_booked_time timestamp, ride_picked_time timestamp, ride_dropped_time timestamp, price double, passenger_mobile_number bigint, rating double, ride_status varchar(20), customer_id int, cab_id int, is_cancelled boolean default false, feedback varchar(255), created_by varchar(30), created_date timestamp, last_modified_by varchar(30), last_modified_date timestamp);
alter table ride add constraint fk_location_id foreign key(pickup_location) references location(id);
alter table ride add constraint fk_location_idd foreign key(drop_location) references location(id);
alter table ride add constraint fk_cab_id foreign key(cab_id) references cab(id);
alter table ride add constraint fk_customer_id foreign key(customer_id) references customer(id);

create table cab(id int primary key auto_increment,driver_name varchar(30),cab_number varchar(15),gender varchar(10),mobile_number bigint unique,email varchar(30) unique,license_number varchar(30) unique,car_model varchar(20),current_location varchar(15),cab_status varchar(20),driver_rating double, cab_category_id int, password varchar(20), is_active boolean default true, created_by varchar(30), created_date timestamp, last_modified_by varchar(30), last_modified_date timestamp);
alter table cab add constraint fk_cab_category_id foreign key(cab_category_id) references cab_category(id);

