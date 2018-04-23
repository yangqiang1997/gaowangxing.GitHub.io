/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : nevis

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-04-11 22:39:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_company_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_company_info`;
CREATE TABLE `sys_company_info` (
  `company_info_id` varchar(50) NOT NULL COMMENT '公司扩展信息id',
  `company_registered_address` varchar(250) NOT NULL COMMENT '公司注册地址',
  `area` varchar(50) DEFAULT NULL COMMENT '公司所属区域',
  `business_license` varchar(50) NOT NULL COMMENT '文件id',
  `uniform_identification_code` varchar(50) NOT NULL COMMENT '公司统一识别码',
  `company_legal_person` varchar(50) NOT NULL COMMENT '公司法人名称',
  `company_legal_person_phone_number` varchar(20) NOT NULL COMMENT '公司法人联系电话',
  `company_status` tinyint(1) NOT NULL COMMENT '公司状态',
  `organization_id` varchar(50) NOT NULL COMMENT '关联组织机构id',
  PRIMARY KEY (`company_info_id`),
  KEY `ci_organization` (`organization_id`),
  CONSTRAINT `ci_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_company_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_data_scope_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_scope_role`;
CREATE TABLE `sys_data_scope_role` (
  `role_id` varchar(50) NOT NULL COMMENT '关联角色',
  `organization_id` varchar(50) NOT NULL COMMENT '管理组织结构',
  KEY `dr_role` (`role_id`),
  KEY `dr_organization` (`organization_id`),
  CONSTRAINT `dr_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`),
  CONSTRAINT `dr_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_scope_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `organization_id` varchar(50) NOT NULL COMMENT '组织机构id',
  `organization_type_id` varchar(50) DEFAULT NULL COMMENT '组织机构类型id',
  `organization_name` varchar(20) DEFAULT NULL COMMENT '组织机构名称',
  `parent_organization_id` varchar(250) DEFAULT NULL COMMENT '关联organization_id',
  `is_expired` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '0（false）未过期，1（true）过期',
  `is_lock` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '0（false）未锁定，1（true）锁定',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `create_id` varchar(50) NOT NULL COMMENT '创建人，关联user_id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人，关联user_id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '0（false）未删除，1（true）删除',
  PRIMARY KEY (`organization_id`),
  KEY `o_organization_type` (`organization_type_id`),
  CONSTRAINT `o_organization_type` FOREIGN KEY (`organization_type_id`) REFERENCES `sys_organization_type` (`organization_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_type`;
CREATE TABLE `sys_organization_type` (
  `organization_type_id` varchar(50) NOT NULL COMMENT '组织机构类型id',
  `organization_type_name` varchar(50) NOT NULL COMMENT '类型名称',
  `organization_extension_info_table_name` varchar(50) NOT NULL COMMENT '扩展信息表明，对应公司和部门的表',
  `add_url` varchar(50) DEFAULT NULL COMMENT '前台新增路由',
  `edit_url` varchar(50) DEFAULT NULL COMMENT '前台编辑路由',
  `detail_url` varchar(50) DEFAULT NULL COMMENT '前台详情路由',
  PRIMARY KEY (`organization_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构类型表';

-- ----------------------------
-- Records of sys_organization_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_user`;
CREATE TABLE `sys_organization_user` (
  `user_id` varchar(50) NOT NULL COMMENT '关联用户',
  `organization_id` varchar(50) NOT NULL COMMENT '关联组织机构',
  KEY `ou_organization` (`organization_id`),
  KEY `ou_user` (`user_id`),
  CONSTRAINT `ou_organization` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`),
  CONSTRAINT `ou_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `resource_name` varchar(20) NOT NULL COMMENT '资源名称',
  `resource_path` varchar(250) DEFAULT NULL COMMENT '资源路由',
  `resource_icon_class` varchar(50) DEFAULT NULL COMMENT 'css类名',
  `resource_show_order` tinyint(4) DEFAULT NULL COMMENT '只有页面资源是菜单时才有顺序',
  `parent_resource_id` varchar(50) DEFAULT NULL COMMENT '自关联',
  `is_menu` tinyint(1) NOT NULL COMMENT '0（false）不是，1（true）是',
  `authorization_token` varchar(50) NOT NULL COMMENT '授权令牌',
  `level0` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_role`;
CREATE TABLE `sys_resource_role` (
  `role_id` varchar(50) NOT NULL COMMENT '关联角色',
  `resource_id` varchar(50) NOT NULL COMMENT '关联资源',
  KEY `rp_role` (`role_id`),
  KEY `rp_resource` (`resource_id`),
  CONSTRAINT `rp_resource` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`resource_id`),
  CONSTRAINT `rp_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `is_allow_edit` tinyint(1) NOT NULL COMMENT '0（false）不允许，1（true）允许',
  `is_allow_delete` tinyint(1) NOT NULL COMMENT '0（false）不允许，1（true）允许',
  `create_id` varchar(50) NOT NULL COMMENT '创建人（对应用户id）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人（对应用户id）',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '0（false）未删除，1（true）删除',
  `data_scope` enum('ALL','COMPANY_AND_CHILD','COMPANY','DEPARTMENT_AND_CHILD','DEPARTMENT','SELF','CUSTOM') DEFAULT 'SELF',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `account` varchar(20) NOT NULL COMMENT '登录账号',
  `password` varchar(250) NOT NULL COMMENT '加密之后的密码',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `user_sex` varchar(20) NOT NULL COMMENT '性别',
  `birthdate` date NOT NULL COMMENT '出生日期',
  `phone_number` varchar(20) NOT NULL COMMENT '联系电话',
  `user_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `wechart_number` varchar(50) DEFAULT NULL COMMENT '微信号',
  `head_portrait` varchar(50) DEFAULT NULL COMMENT '文件id',
  `working_status` tinyint(1) DEFAULT NULL COMMENT '0（false）离职，1（true）在职',
  `is_expired` tinyint(1) DEFAULT 0 COMMENT '0（false）未过期，1（true）过期',
  `is_lock` tinyint(1) DEFAULT 0 COMMENT '0（false）未锁定，1（true）锁定',
  `is_disabled` tinyint(1) DEFAULT 0 COMMENT '0（false）未禁用，1（true）禁用',
  `user_remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `create_id` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '0（false）未删除，1（true）删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('a3f53a50-93f8-4a71-9f06-b7da65214687', 'admin', '$2a$10$Ghs/8Y1MErNulZBRJHQbWOGj87SpcfU684O2xyJMK476sruauA9Si', 'admin', '男', NOW(), '110', null, null, null, null, null, '0', '0', '0', null, 'a3f53a50-93f8-4a71-9f06-b7da65214687', NOW(), null, null, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(50) NOT NULL COMMENT '关联用户id',
  `role_id` varchar(50) NOT NULL COMMENT '关联角色id',
  KEY `ur_user` (`user_id`),
  KEY `ur_role` (`role_id`),
  CONSTRAINT `ur_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `ur_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------


