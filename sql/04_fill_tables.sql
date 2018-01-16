INSERT INTO `user` 
(`user_id`, `login`,         `password`, `last_name`, `first_name`, `middle_name`, `role`) 
VALUES 
(1,         'администратор1','12345',    'Петров',    'Петр',       'Петрович',     0), 
(2,         'водитель1',     '12345',    'Иванов',    'Иван',       'Иванович',     2), 
(3,         'диспетчер1',    '12345',    'Сидоров',   'Сергей',     'Николаевич',   1), 
(4,         'водитель2',     '12345',    'Алексеев',  'Андрей',     'Сергеевич',    2);

INSERT INTO `car` 
(`car_id`, `model`, `places`, `carrying`, `condition`) 
VALUES 
(1,         0,       2,        NULL,       0), 
(2,         1,       NULL,     3,          1);

INSERT INTO `driver` 
(`driver_id`, `car_id`) 
VALUES 
(1,            1), 
(2,            2);

INSERT INTO `request` 
(`request_id`, `driver_id`, `description`, `status`) 
VALUES 
(1,             1,          'Витебск-Минск',0), 
(2,             2,          'Полоцк-Москва',1);