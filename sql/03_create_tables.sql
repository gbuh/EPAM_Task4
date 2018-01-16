USE `motor_depot_db`; 

CREATE TABLE `user` (
    `user_id`     INT NOT NULL AUTO_INCREMENT, 
    `login`       VARCHAR(255) NOT NULL, 
    `password`    VARCHAR(255) NOT NULL, 
    `last_name`   VARCHAR(255) NOT NULL, 
    `first_name`  VARCHAR(255) NOT NULL, 
    `middle_name` VARCHAR(255) NOT NULL, 
    `role`        TINYINT NOT NULL, 
    PRIMARY KEY (`user_id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `car` (
    `car_id`    INT NOT NULL AUTO_INCREMENT, 
    `model`     TINYINT NOT NULL, 
    `places`    TINYINT, 
    `carrying`  TINYINT, 
    `condition` TINYINT NOT NULL, 
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
    `status`      TINYINT NOT NULL, 
    PRIMARY KEY (`request_id`),
    FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)  ENGINE=INNODB DEFAULT CHARACTER SET = UTF8;