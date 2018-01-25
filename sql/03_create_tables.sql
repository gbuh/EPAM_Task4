USE `motor_depot_db`; 

CREATE TABLE `user` (
    `user_id`     INT NOT NULL AUTO_INCREMENT, 
    `login`       VARCHAR(255) NOT NULL, 
    `password`    VARCHAR(255) NOT NULL, 
    `last_name`   VARCHAR(255) NOT NULL, 
    `first_name`  VARCHAR(255) NOT NULL, 
    `middle_name` VARCHAR(255) NOT NULL, 
    `role`        TINYINT NOT NULL COMMENT '0 - driver, 1 - dispatcher, 2 - admin', 
    PRIMARY KEY (`user_id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `car` (
    `car_id`    INT NOT NULL AUTO_INCREMENT, 
    `model`     TINYINT NOT NULL COMMENT '0 - automobile, 1 - truck, 2 - bus', 
    `places`    TINYINT COMMENT 'number of seats', 
    `carrying`  TINYINT COMMENT 'carring in tons', 
    `condition` TINYINT NOT NULL COMMENT '0 - good, 1 - defective', 
    PRIMARY KEY (`car_id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `driver` (
    `driver_id` INT NOT NULL AUTO_INCREMENT, 
    `car_id`    INT NOT NULL,
    PRIMARY KEY (`driver_id`),
   	FOREIGN KEY (`driver_id`) REFERENCES `user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT, 
   	FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `request` (
    `request_id`  INT NOT NULL AUTO_INCREMENT, 
    `driver_id`   INT NOT NULL,
    `description` VARCHAR(255) NOT NULL, 
    `status`      TINYINT NOT NULL COMMENT '0 - accept, 1 - execution, 2 - done', 
    PRIMARY KEY (`request_id`),
    FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;