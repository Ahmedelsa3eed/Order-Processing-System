create schema bookstore;
use bookstore;
CREATE TABLE `Users` (
  `user_id` bigint,
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

CREATE TABLE `Orders` (
  `order_id` bigint,
  `user_id` bigint,
  `shipping_address` varchar(100),
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)
);

CREATE TABLE `Publisher` (
  `publisger_id` bigint,
  `first_name` varchar(20),
  `last_name` varchar(20),
  `address` varchar(50),
  `phone_number` varchar(15),
  PRIMARY KEY (`publisger_id`)
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
  FOREIGN KEY (`publisher_id`) REFERENCES `Publisher`(`publisger_id`)
);

CREATE TABLE `Orders_items` (
  `order_id` bigint,
  `ISBN` bigint,
  `quantity` int,
  PRIMARY KEY (`order_id`, `ISBN`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`),
  FOREIGN KEY (`order_id`) REFERENCES `Orders`(`order_id`)
);

CREATE TABLE `credit_card` (
  `card_number` bigint,
  `user_id` bigint,
  `expiry_date` Date,
  PRIMARY KEY (`card_number`)
);

CREATE TABLE `Authors` (
  `authoer_id` bigint,
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
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`),
  FOREIGN KEY (`ISBN`) REFERENCES `Books`(`ISBN`)
);