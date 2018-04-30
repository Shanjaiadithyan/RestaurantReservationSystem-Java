Application Name: Restaurant Reservation System

To run the app in browser : http://localhost:8000/RestaurantReservationApp/

Application created using-
Language: Java 1.8 , J2EE
Database: MySQL 5.7
Server: Tomcat 7.0
IDE: Eclipse

Required jar: mysql-connector-java-5.0.8-bin.jar

Steps to create project:
Eclipse -> File -> New -> Dynamic Web Project

Setup MySQL database before running the app-
Database name: restaurantreservation
Username: root
Password: root

Tables to be created:
1)	customer
2)	day_availability
3)	reservation
4)	restaurant


create table day_availability
(
day_of_week varchar(15) primary key,
available_tables int
);


insert into day_availability values('Sunday',10);
insert into day_availability values('Monday',10);
insert into day_availability values('Tuesday',10);
insert into day_availability values('Wednesday',10);
insert into day_availability values('Thursday',10);
insert into day_availability values('Friday',10);
insert into day_availability values('Saturday',10);


create table customer
(
cust_id int primary key auto_increment,
cust_name varchar(50),
pswd varchar(50)
);


create table restaurant
(
rest_id int primary key,
rest_name varchar(20)
);


insert into restaurant values(100,'Taj');

create table reservation
(
reservation_id int primary key auto_increment,
restaurant_id int,
customer_id int,
tables_reqd int,
reservation_day varchar(15),
reservation_time time,
foreign key (restaurant_id) references restaurant(rest_id),
foreign key (customer_id) references customer(cust_id),
foreign key (reservation_day) references day_availability(day_of_week)
);
