/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : javaee

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-04-22 21:33:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `attachment_id` varchar(50) NOT NULL COMMENT '附件id',
  `attachment_name` varchar(50) DEFAULT NULL COMMENT '附件名称',
  `attachment_content` blob COMMENT '附件内容',
  `attachment_type` varchar(50) DEFAULT NULL COMMENT '附件类型',
  `add_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_personal_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_personal_user`;
CREATE TABLE `sys_personal_user` (
  `personal_user_id` varchar(50) NOT NULL COMMENT '个人用户信息id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户表id',
  `document_type` varchar(50) DEFAULT NULL COMMENT '证件类型',
  `document_no` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `id_card_front` varchar(50) DEFAULT NULL COMMENT '身份证正面照片',
  `id_card_back` varchar(50) DEFAULT NULL COMMENT '身份证背面照片',
  PRIMARY KEY (`personal_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `sys_user` ADD COLUMN `check_status` TINYINT(1) DEFAULT 0 COMMENT '是否审核，0（false）未审核，1（true）已经审核';
ALTER TABLE `sys_company_info` ADD COLUMN `enterprise_type` VARCHAR(50) NOT NULL COMMENT '企业类型';
ALTER TABLE `sys_company_info` ADD COLUMN `company_legal_person_sex` VARCHAR(4) NOT NULL COMMENT '法人性别';
ALTER TABLE `sys_company_info` ADD COLUMN `company_legal_person_document_type` VARCHAR(50) NOT NULL COMMENT '法人证件类型';
ALTER TABLE `sys_company_info` ADD COLUMN `company_legal_person_document_no` VARCHAR(50) NOT NULL COMMENT '法人证件号码';
ALTER TABLE `sys_company_info` ADD COLUMN `company_legal_person_id_card_front` VARCHAR(50) NOT NULL COMMENT '法人身份证正面';
ALTER TABLE `sys_company_info` ADD COLUMN `company_legal_person_id_card_back` VARCHAR(50) NOT NULL COMMENT '法人身份证背面';