CREATE DATABASE `motor_depot_db` DEFAULT CHARACTER SET utf8;

GRANT ALL PRIVILEGES
ON `motor_depot_db`.*
TO administrator@localhost
IDENTIFIED BY 'admin_password';

GRANT SELECT
ON `motor_depot_db`.*
TO dispatcher@localhost
IDENTIFIED BY 'disp_password',
driver@localhost
IDENTIFIED BY 'driver_password';

GRANT INSERT,UPDATE,DELETE
ON `motor_depot_db`.`request`
TO dispatcher@localhost
IDENTIFIED BY 'disp_password';

GRANT INSERT,UPDATE,DELETE
ON `motor_depot_db`.`car`
TO driver@localhost
IDENTIFIED BY 'driver_password';