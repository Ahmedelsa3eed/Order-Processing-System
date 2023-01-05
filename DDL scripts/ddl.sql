create schema bookstore;
use bookstore;

CREATE TABLE `Publishers` (
  `publisher_id`  bigint  AUTO_INCREMENT,
  `name` varchar(20) Not Null,
  `address` varchar(50) Not Null,
  `phone_number` varchar(15) Not Null,
  PRIMARY KEY (`publisher_id`)
);

CREATE TABLE `Users` (
  `user_id` bigint AUTO_INCREMENT,
  `user_name` varchar(20) Not Null,
  `first_name` varchar(20) Not Null,
  `last_name` varchar(20) Not Null,
  `address` varchar(50),
  `phone_number` varchar(15) Not Null,
  `email` varchar(70) Not Null,
  `password` varchar(50) Not Null,
  `type` ENUM("customer", "manager")  default("customer"),
  PRIMARY KEY (`user_id`)
);
ALTER TABLE Users 
ADD CONSTRAINT unique_name UNIQUE (user_name);

ALTER TABLE Users
ADD CONSTRAINT unique_email UNIQUE(email);

CREATE TABLE `credit_card` (
  `card_number` bigint Not Null,
  `user_id` bigint Not Null,
  `expiry_date` Date Not Null,
  PRIMARY KEY (`card_number`),
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)  on delete cascade on update cascade
);

CREATE TABLE `Books` (
  `ISBN` bigint Not Null,
  `title` varchar(50) Not Null,
  `publisher_id` bigint Not Null,
  `publication_year` int(4) Not Null,
  `price` double Not Null,
  `category`   ENUM("Science", "Art", "Religion", "History", "Geography") Not Null,
  `quantity` int Not Null,
  `threshold` int default(0),
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`publisher_id`) REFERENCES `Publishers`(`publisher_id`)  on delete cascade on update cascade
);

CREATE TABLE `Authors` (
  `author_id`  bigint AUTO_INCREMENT,
  `first_name` varchar(20) Not Null,
  `last_name` varchar(20) Not Null,
  `address` varchar(50) Not Null,
  `phone_number` varchar(15) Not Null,
  PRIMARY KEY (`author_id`)
);

CREATE TABLE `Book_Authors` (
  `author_id` bigint Not Null,
  `ISBN` bigint Not Null,
  PRIMARY KEY (`author_id`, `ISBN`),
  FOREIGN KEY (`author_id`) REFERENCES `Authors`(`author_id`)  on delete cascade on update cascade,
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`) on delete cascade on update cascade
);

CREATE TABLE `Cart` (
  `checkout_id` Not NUll AUTO_INCREMENT,
  `ISBN` bigint Not Null,
  `user_id` bigint Not Null,
  `quantity` int Not Null,
  `confirmed` bit Not Null,
  PRIMARY KEY (`checkout_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)  on delete cascade on update cascade,
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) on delete cascade on update cascade
);

CREATE TABLE `checkout` (
  `ISBN` bigint Not Null,
  `user_id` bigint Not Null,
  `quantity` int Not Null,
  `order_date` Date,
  PRIMARY KEY (`ISBN`, `user_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)  on delete cascade on update cascade,
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) on delete cascade on update cascade
);

CREATE TABLE `Orders` (
  `order_id`  bigint AUTO_INCREMENT,
  `ISBN` bigint,
  `quantity` int,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)  on delete cascade on update cascade
);

