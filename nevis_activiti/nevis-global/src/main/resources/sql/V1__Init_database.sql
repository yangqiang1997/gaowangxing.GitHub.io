/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : spring-boot

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-04-04 23:00:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_company_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_company_info`;
CREATE TABLE `sys_company_info` (
  `company_info_id` varchar(50) NOT NULL COMMENT '公司扩展信息id',
  `company_registered_address` varchar(250) DEFAULT NULL COMMENT '公司注册地址',
  `area` varchar(50) DEFAULT NULL COMMENT '公司所属区域',
  `business_license` varchar(50) DEFAULT NULL COMMENT '文件id',
  `uniform_identification_code` varchar(50) DEFAULT NULL COMMENT '公司统一识别码',
  `company_legal_person` varchar(50) DEFAULT NULL COMMENT '公司法人名称',
  `company_legal_person_phone_number` varchar(20) DEFAULT NULL COMMENT '公司法人联系电话',
  `company_status` tinyint(1) DEFAULT NULL COMMENT '公司状态',
  `organization_id` varchar(50) DEFAULT NULL COMMENT '关联组织机构id',
  PRIMARY KEY (`company_info_id`),
  KEY `ci_organization` (`organization_id`),
  CONSTRAINT `ci_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_data_range
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_range`;
CREATE TABLE `sys_data_range` (
  `role_id` varchar(50) DEFAULT NULL COMMENT '关联角色',
  `organization_id` varchar(50) DEFAULT NULL COMMENT '管理组织结构',
  KEY `dr_role` (`role_id`),
  KEY `dr_organization` (`organization_id`),
  CONSTRAINT `dr_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`),
  CONSTRAINT `dr_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `organization_id` varchar(50) NOT NULL COMMENT '组织机构id',
  `organization_type_id` varchar(50) DEFAULT NULL COMMENT '组织机构类型id',
  `organization_name` varchar(20) DEFAULT NULL COMMENT '组织机构名称',
  `parent_organization_id` varchar(250) DEFAULT NULL COMMENT '关联organization_id',
  `is_expired` tinyint(1) DEFAULT NULL COMMENT '0（false）过期，1（true）未过期',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '0（false）锁定，1（true）未锁定',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人，关联user_id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人，关联user_id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '0（false）未删除，1（true）删除',
  PRIMARY KEY (`organization_id`),
  KEY `o_organization_type` (`organization_type_id`),
  CONSTRAINT `o_organization_type` FOREIGN KEY (`organization_type_id`) REFERENCES `sys_organization_type` (`organization_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_organization_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_type`;
CREATE TABLE `sys_organization_type` (
  `organization_type_id` varchar(50) NOT NULL COMMENT '组织机构类型id',
  `organization_type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `organization_extension_info_table_name` varchar(50) DEFAULT NULL COMMENT '扩展信息表明，对应公司和部门的表',
  `add_url` varchar(50) DEFAULT NULL COMMENT '前台新增路由',
  `edit_url` varchar(50) DEFAULT NULL COMMENT '前台编辑路由',
  `detail_url` varchar(50) DEFAULT NULL COMMENT '前台详情路由',
  PRIMARY KEY (`organization_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构类型表';

-- ----------------------------
-- Table structure for sys_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_user`;
CREATE TABLE `sys_organization_user` (
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户',
  `organization_id` varchar(50) DEFAULT NULL COMMENT '关联组织机构',
  KEY `ou_organization` (`organization_id`),
  KEY `ou_user` (`user_id`),
  CONSTRAINT `ou_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`),
  CONSTRAINT `ou_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `resource_name` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `resource_path` varchar(250) DEFAULT NULL COMMENT '资源路由',
  `resource_icon_class` varchar(50) DEFAULT NULL COMMENT 'css类名',
  `resource_show_order` tinyint(4) DEFAULT NULL COMMENT '只有页面资源是菜单时才有顺序',
  `parent_resource_id` varchar(50) DEFAULT NULL COMMENT '自关联',
  `is_menu` tinyint(1) DEFAULT NULL COMMENT '0（false）不是，1（true）是',
  `authorization_token` varchar(50) DEFAULT NULL COMMENT '授权令牌',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_resource_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_permission`;
CREATE TABLE `sys_resource_permission` (
  `role_id` varchar(50) DEFAULT NULL COMMENT '关联角色',
  `resource_id` varchar(50) DEFAULT NULL COMMENT '关联资源',
  KEY `rp_role` (`role_id`),
  KEY `rp_resource` (`resource_id`),
  CONSTRAINT `rp_resource` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`resource_id`),
  CONSTRAINT `rp_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `is_allow_edit` tinyint(1) DEFAULT NULL COMMENT '0（false）不允许，1（true）允许',
  `is_allow_delete` tinyint(1) DEFAULT NULL COMMENT '0（false）不允许，1（true）允许',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人（对应用户id）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人（对应用户id）',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '0（false）未删除，1（true）删除',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `account` varchar(20) DEFAULT NULL COMMENT '登录账号',
  `·password·` varchar(20) DEFAULT NULL COMMENT '登录密码',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `user_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `wechart_number` varchar(50) DEFAULT NULL COMMENT '微信号',
  `head_portrait` varchar(50) DEFAULT NULL COMMENT '文件id',
  `working_status` tinyint(1) DEFAULT NULL COMMENT '0（false）离职，1（true）在职',
  `is_expired` tinyint(1) DEFAULT NULL COMMENT '0（false）过期，1（true）未过期',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '0（false）锁定，1（true）未锁定',
  `user_status` tinyint(4) DEFAULT NULL COMMENT '暂定',
  `user_remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '0（false）删除，1（true）未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户id',
  `role_id` varchar(50) DEFAULT NULL COMMENT '关联角色id',
  KEY `ur_user` (`user_id`),
  KEY `ur_role` (`role_id`),
  CONSTRAINT `ur_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `ur_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_code_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_table`;
CREATE TABLE `sys_code_table` (
  `param_code` varchar(50) NOT NULL,
  `param_name` varchar(50) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL COMMENT '关联param_code',
   `level0`  tinyint(4) DEFAULT NULL COMMENT '层级',
   `code_type` varchar(50) DEFAULT NULL COMMENT '码表类型',
   `has_child` tinyint(1) DEFAULT NULL COMMENT '0没有1有',
  PRIMARY KEY  (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
