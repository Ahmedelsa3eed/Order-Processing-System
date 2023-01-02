create schema bookstore;
use bookstore;
CREATE TABLE `Publishers` (
  `publisher_id`  bigint  AUTO_INCREMENT,
  `name` varchar(20),
  `address` varchar(50),
  `phone_number` varchar(15),
  PRIMARY KEY (`publisher_id`)
);

CREATE TABLE `Users` (
  `user_id` bigint AUTO_INCREMENT,
  `user_name` varchar(20),
  `first_name` varchar(20),
  `last_name` varchar(20),
  `address` varchar(50),
  `phone_number` varchar(15),
  `email` varchar(70),
  `password` varchar(50),
  `type` ENUM("custumer", "manager"),
  PRIMARY KEY (`user_id`)
);
ALTER TABLE Users 
ADD CONSTRAINT unique_name UNIQUE (user_name);

ALTER TABLE Users
ADD CONSTRAINT unique_email UNIQUE(email);

ALTER TABLE Users 
ADD CONSTRAINT unique_name UNIQUE (user_name);

ALTER TABLE Users
ADD CONSTRAINT unique_email UNIQUE(email);

CREATE TABLE `credit_card` (
  `card_number` bigint,
  `user_id` bigint,
  `expiry_date` Date,
  PRIMARY KEY (`card_number`),
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)
);

CREATE TABLE `Books` (
  `ISBN` bigint,
  `title` varchar(50),
  `publisher_id` bigint,
  `publication_year` int(4),
  `price` int,
  `category`   ENUM("Science", "Art", "Religion", "History", "Geography"),
  `quantity` int,
  `threshold` int,
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`publisher_id`) REFERENCES `Publishers`(`publisher_id`)
);

CREATE TABLE `Authors` (
  `authoer_id`  bigint AUTO_INCREMENT,
  `first_name` varchar(20),
  `last_name` varchar(20),
  `address` varchar(50),
  `phone_number` varchar(15),
  PRIMARY KEY (`authoer_id`)
);

CREATE TABLE `Book_Authors` (
  `authoer_id` bigint,
  `ISBN` bigint,
  PRIMARY KEY (`authoer_id`, `ISBN`),
  FOREIGN KEY (`authoer_id`) REFERENCES `Authors`(`authoer_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)
);

CREATE TABLE `Cart` (
  `ISBN` bigint,
  `user_id` bigint,
  `quantity` int,
  PRIMARY KEY (`ISBN`, `user_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`),
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)
);

CREATE TABLE `Orders` (
  `order_id`  bigint AUTO_INCREMENT,
  `ISBN` bigint,
  `quantity` int,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)
);

