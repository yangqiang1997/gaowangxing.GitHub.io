-- 先清除表数据
DELETE FROM `sys_resource_role`;
DELETE FROM `sys_resource`;
DELETE FROM `sys_user_role`;
DELETE FROM `sys_role`;

-- 新增超级管理员角色
INSERT INTO `sys_role` (`role_id`, `role_name`, `is_allow_edit`, `is_allow_delete`, `create_id`, `create_time`, `update_id`, `update_time`, `is_delete`, `data_scope`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', 'admin', '0', '0', 'a3f53a50-93f8-4a71-9f06-b7da65214687', '2018-04-12 15:19:18', NULL, NULL, '0', 'SELF');
-- 新增超级管理员角色和用户关联
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('a3f53a50-93f8-4a71-9f06-b7da65214687', '7423fdab-e5c5-47ab-b0b4-9d8969aba531');
-- 新增资源数据
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('00000001', '基础数据管理', '/', '', '1', NULL, '1', 'BASE_DATA', '0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('0000000100000001', '资源管理', '/resource', '', '1', '00000001', '1', 'RESOURCE', '0-0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000100000001', '新增', '', '', NULL, '0000000100000001', '0', 'RESOURCE_ADD', '0-0-0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000100000002', '修改', '', '', NULL, '0000000100000001', '0', 'RESOURCE_UPDATE', '0-0-1');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000100000003', '删除', '', '', NULL, '0000000100000001', '0', 'RESOURCE_DELETE', '0-0-2');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000100000004', '查看', '', '', NULL, '0000000100000001', '0', 'RESOURCE_DETAIL', '0-0-3');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('0000000100000002', '角色管理', '/roleManagement', '', '2', '00000001', '1', 'ROLE', '0-1');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000200000001', '新增', '', '', NULL, '0000000100000002', '0', 'ROLE_ADD', '0-1-0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000200000002', '修改', '', '', NULL, '0000000100000002', '0', 'REOLE_UPDATE', '0-1-1');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000200000003', '删除', '', '', NULL, '0000000100000002', '0', 'ROLE_DELETE', '0-1-2');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000200000004', '查看', '', '', NULL, '0000000100000002', '0', 'ROLE_DETAIL', '0-1-3');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('0000000100000003', '组织机构管理', '/orgManagment', '', '3', '00000001', '1', 'ORG', '0-2');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000300000001', '新增', '', '', NULL, '0000000100000003', '0', 'ORG_ADD', '0-2-0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000300000002', '修改', '', '', NULL, '0000000100000003', '0', 'ORG_UPDATE', '0-2-1');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000300000003', '删除', '', '', NULL, '0000000100000003', '0', 'ORG_DELETE', '0-2-2');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000300000004', '查看', '', '', NULL, '0000000100000003', '0', 'ORG_DETAIL', '0-2-3');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('0000000100000004', '用户管理', '/userManagement', '', '4', '00000001', '1', 'USER', '0-3');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000400000001', '新增', '', '', NULL, '0000000100000004', '0', 'USER_ADD', '0-3-0');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000400000002', '删除', '', '', NULL, '0000000100000004', '0', 'USER_DELETE', '0-3-1');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000400000003', '修改', '', '', NULL, '0000000100000004', '0', 'USER_UPDATE', '0-3-2');
INSERT INTO `sys_resource` (`resource_id`, `resource_name`, `resource_path`, `resource_icon_class`, `resource_show_order`, `parent_resource_id`, `is_menu`, `authorization_token`, `level0`) VALUES ('000000010000000400000004', '查看', '', '', NULL, '0000000100000004', '0', 'USER_DETAIL', '0-3-3');
-- 新增超级管理员资源权限
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '00000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '0000000100000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000100000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000100000002');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000100000003');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000100000004');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '0000000100000002');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000200000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000200000002');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000200000003');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000200000004');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '0000000100000003');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000300000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000300000002');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000300000003');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000300000004');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '0000000100000004');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000400000001');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000400000002');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000400000003');
INSERT INTO `sys_resource_role` (`role_id`, `resource_id`) VALUES ('7423fdab-e5c5-47ab-b0b4-9d8969aba531', '000000010000000400000004');