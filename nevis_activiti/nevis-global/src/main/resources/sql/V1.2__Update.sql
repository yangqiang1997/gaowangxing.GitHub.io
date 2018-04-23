ALTER TABLE `sys_resource_permission` RENAME TO `sys_resource_role`;
ALTER TABLE `sys_role` ALTER COLUMN `data_scope` SET DEFAULT 'SELF';
ALTER TABLE `sys_data_range` RENAME TO `sys_data_scope_role`;