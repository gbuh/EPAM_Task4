DROP DATABASE IF EXISTS `motor_depot_db`; 

CREATE DATABASE `motor_depot_db` DEFAULT CHARACTER SET utf8; 

USE `motor_depot_db`; 

CREATE TABLE `user` (
    `user_id`  INT NOT NULL AUTO_INCREMENT, 
    `login`    VARCHAR(255) NOT NULL, 
    `password` VARCHAR(255) NOT NULL, 
    `role`     TINYINT NOT NULL, 
    PRIMARY KEY (`user_id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `admin` (
    `admin_id`    INT NOT NULL AUTO_INCREMENT, 
    `last_name`   VARCHAR(255) NOT NULL, 
    `first_name`  VARCHAR(255) NOT NULL, 
    `middle_name` VARCHAR(255) NOT NULL, 
    PRIMARY KEY (`admin_id`),
   	FOREIGN KEY (`admin_id`) REFERENCES `user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `dispatcher` (
    `dispatcher_id` INT NOT NULL AUTO_INCREMENT, 
    `last_name`     VARCHAR(255) NOT NULL, 
    `first_name`    VARCHAR(255) NOT NULL, 
    `middle_name`   VARCHAR(255) NOT NULL, 
    PRIMARY KEY (`dispatcher_id`),
   	FOREIGN KEY (`dispatcher_id`) REFERENCES `user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT  
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `request` (
    `request_id`    INT NOT NULL AUTO_INCREMENT, 
	`dispatcher_id` INT NOT NULL, 
    `description`   VARCHAR(255) NOT NULL, 
    PRIMARY KEY (`request_id`),
   	FOREIGN KEY (`dispatcher_id`) REFERENCES `dispatcher` (`dispatcher_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `trip` (
    `trip_id`    INT NOT NULL AUTO_INCREMENT, 
	`request_id` INT NOT NULL, 
    `status`     TINYINT NOT NULL, 
    PRIMARY KEY (`trip_id`),
   	FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `driver` (
    `driver_id`   INT NOT NULL AUTO_INCREMENT, 
    `request_id`    INT NOT NULL, 
    `last_name`   VARCHAR(255) NOT NULL, 
    `first_name`  VARCHAR(255) NOT NULL, 
    `middle_name` VARCHAR(255) NOT NULL, 
    PRIMARY KEY (`driver_id`),
   	FOREIGN KEY (`driver_id`) REFERENCES `user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT, 
   	FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `car` (
    `car_id`    INT NOT NULL AUTO_INCREMENT, 
    `driver_id` INT NOT NULL, 
    `trip_id`   INT NOT NULL,    
    `model`     TINYINT NOT NULL, 
    `plases`    TINYINT, 
    `carrying`  TINYINT, 
    `condition` TINYINT NOT NULL, 
    PRIMARY KEY (`car_id`),
   	FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON UPDATE RESTRICT ON DELETE RESTRICT, 
   	FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;
