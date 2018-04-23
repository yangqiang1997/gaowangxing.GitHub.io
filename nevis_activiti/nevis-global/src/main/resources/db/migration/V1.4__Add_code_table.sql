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