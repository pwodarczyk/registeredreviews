create database registered_reviews;
use registered_reviews;

/* Business Table */
drop table if exists registered_reviews.business;
create table registered_reviews.business 
(id int auto_increment,
name varchar(60) not null,
description varchar(300),
address varchar(80),
city varchar(50),
state varchar(30),
postal varchar(10), 
latitude float(10,6), 
longitude float(10,6), 
google_api_id varchar(100),
phone varchar(25), 
website varchar(255), 
email varchar (255), 
created_date datetime, 
primary key (id)) 
ENGINE=INNODB;

drop table if exists registered_reviews.user_roles;
CREATE TABLE user_roles (
  user_role_id int(11) auto_increment NOT NULL ,
  user_id int(11) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id))
ENGINE=INNODB;

drop table if exists registered_reviews.site_user;
create table registered_reviews.site_user 
(id int auto_increment,
first_name varchar(20) not null,
last_name varchar(40),
address varchar(80),
city varchar(50),
state varchar(30),
postal varchar(10),  
phone varchar(25), 
email varchar (255) not null, 
password varchar (100) not null,
verified boolean not null default false,
created_date date, 
enabled TINYINT NOT NULL DEFAULT 1 ,
primary key (id)) 
ENGINE=INNODB;

drop table if exists registered_reviews.promotion;
create table registered_reviews.promotion 
(id int auto_increment,
business_id int not null,
name varchar(60),
description varchar(300),
start_date date, 
end_date date, 
created_date date, 
primary key (id)) 
ENGINE=INNODB;

drop table if exists registered_reviews.review;
create table registered_reviews.review 
(id int auto_increment,
site_user_id int not null,
business_id int not null,
rating float (2,1),
review varchar(500), 
created_date date, 
deleted boolean not null default false,
primary key (id)) 
ENGINE=INNODB;

drop table if exists registered_reviews.business_user;
create table registered_reviews.business_user 
(id int auto_increment,
site_user_id int not null,
business_id int not null,
social_sec varchar(15),
trusted_user boolean not null default false,
created_date date, 
primary key (id)) 
ENGINE=INNODB;


INSERT INTO site_user(first_name,last_name,email,password,enabled,created_date)
VALUES ('phil','wodarczyk','philwod@gmail.com','phil', true, now());

INSERT INTO user_roles (user_id, role)
VALUES ('6', 'ROLE_USER');
