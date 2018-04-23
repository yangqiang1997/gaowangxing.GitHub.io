/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nevis

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-19 14:31:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activiti_task_record
-- ----------------------------
DROP TABLE IF EXISTS `activiti_task_record`;
CREATE TABLE `activiti_task_record` (
  `process_instance_id` varchar(100) NOT NULL COMMENT '流程实例id',
  `task_id` varchar(100) NOT NULL COMMENT '任务id',
  `complete_user_id` varchar(100) NOT NULL COMMENT '完成用户id',
  `complete_time` datetime NOT NULL COMMENT '完成时间',
  `node_order` int(255) NOT NULL COMMENT '节点顺序',
  `node_data_id` varchar(100) DEFAULT NULL  COMMENT '节点相关数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;