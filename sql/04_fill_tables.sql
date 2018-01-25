INSERT INTO `user` 
(`user_id`, `login`,         `password`, `last_name`, `first_name`, `middle_name`, `role`) 
VALUES 
(1,         'администратор1','12345',    'Петров',    'Петр',       'Петрович',     2), 
(2,         'диспетчер1',    '12345',    'Сидоров',   'Сергей',     'Николаевич',   1), 
(3,         'водитель1',     '12345',    'Иванов',    'Иван',       'Иванович',     0), 
(4,         'водитель2',     '12345',    'Алексеев',  'Андрей',     'Сергеевич',    0), 
(5,         'водитель3',     '12345',    'Янов',      'Ян',         'Янович',       0);

INSERT INTO `car` 
(`car_id`, `model`, `places`, `carrying`, `condition`) 
VALUES 
(1,         0,       2,        NULL,       0), 
(2,         1,       NULL,     3,          1),
(3,         2,       15,       NULL,       0);

INSERT INTO `driver` 
(`driver_id`, `car_id`) 
VALUES 
(3,            1), 
(4,            2), 
(5,            3);

INSERT INTO `request` 
(`request_id`, `driver_id`, `description`,      `status`) 
VALUES 
(1,             3,          'Витебск-Минск',     0), 
(2,             4,          'Полоцк-Москва',     1), 
(3,             5,          'Орша-Могилев',      2), 
(4,             5,          'Экскурсия в Несвиж',0);