ALTER TABLE `sys_user` CHANGE `user_status` `is_disabled` TINYINT(1);
ALTER TABLE `sys_user` CHANGE `·password·` `password` VARCHAR(250);
INSERT INTO `sys_user` (`user_id`, `account`, `password`, `is_expired`, `is_lock`, `is_disabled`, `is_delete`) VALUE (
    'a3f53a50-93f8-4a71-9f06-b7da65214687',
    'admin',
    /*密码是admin,这个值是经过加密的*/
    '$2a$10$Ghs/8Y1MErNulZBRJHQbWOGj87SpcfU684O2xyJMK476sruauA9Si',
    0, 0, 0, 0
);