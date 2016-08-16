create database real_time_service;
use real_time_service;

/* Business Table */
CREATE TABLE IF NOT EXISTS real_time_service.business 
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

CREATE TABLE IF NOT EXISTS real_time_service.site_user 
(id int auto_increment,
name varchar (60) not null, 
email varchar (255), 
postal varchar(10) not null, 
phone_number varchar(15), 
created_date date, 
enabled TINYINT NOT NULL DEFAULT 1,
primary key (id)) 
ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS real_time_service.service 
(id int auto_increment,
name varchar(100),
description varchar(200),
created_date date,
primary key (id)) 
ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS real_time_service.service_lead 
(id int auto_increment,
site_user_id int not null,
service_id int not null,
subject varchar (200),
comments varchar(500), 
number_filled int,
created_date date, 
link_token varchar(100),
deleted boolean not null default false,
primary key (id)) 
ENGINE=INNODB;



CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

INSERT INTO site_user(first_name,last_name,email,password,enabled,created_date)
VALUES ('phil','wodarczyk','philwod@gmail.com','philwod', true, now());

INSERT INTO admin (username, password, email)
VALUES ('philwod', 'philwod', 'philwod@gmail.com');
