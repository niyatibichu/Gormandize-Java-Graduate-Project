CREATE SCHEMA `gormandize` ;

CREATE TABLE `gormandize`.`users`(
  user_id INT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(80) NOT NULL,
  password CHAR(41) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE `gormandize`.`restaurants` (
  restaurant_id INT NOT NULL AUTO_INCREMENT,
  restaurant_name VARCHAR(50) NOT NULL,
  average_rating INT NOT NULL,
  count INT NOT NULL,
  PRIMARY KEY (restaurant_id)
);

CREATE TABLE `gormandize`.`reviews` (
  review_id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  review VARCHAR(100) NOT NULL,
  rating INT NOT NULL,
  PRIMARY KEY (review_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id)
);


INSERT INTO `gormandize`.`restaurants` (`restaurant_name`, `average_rating`, `count`) VALUES ('Dog Haus', '3', '1');
INSERT INTO `gormandize`.`restaurants` (`restaurant_name`, `average_rating`, `count`) VALUES ('Panda Express', '4', '1');







 