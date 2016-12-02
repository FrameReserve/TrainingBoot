/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.63
Source Server Version : 50631
Source Host           : 192.168.2.63:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-12-02 11:45:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_sysmgr_aclrequesttype`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclrequesttype`;
CREATE TABLE `tbl_sysmgr_aclrequesttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL COMMENT '请求类型',
  `pronoun` varchar(200) NOT NULL COMMENT '请求类型代码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `pronoun` (`pronoun`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclrequesttype
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('1', '增加', 'add', '2016-07-12 22:25:27', '2016-07-12 22:25:30');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('2', '读取', 'query', '2016-07-13 15:10:13', '2016-07-13 15:10:18');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('3', '更新', 'update', '2016-07-13 15:10:24', '2016-07-13 15:10:20');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('4', '删除', 'delete', '2016-07-13 15:10:27', '2016-07-13 15:10:22');

-- ----------------------------
-- Table structure for `tbl_sysmgr_aclresources`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclresources`;
CREATE TABLE `tbl_sysmgr_aclresources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(200) NOT NULL COMMENT '资源地址',
  `type` varchar(50) NOT NULL COMMENT '资源类型',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `pronoun` varchar(50) NOT NULL COMMENT '资源代码',
  `parent_id` int(11) DEFAULT NULL COMMENT '请求类型',
  `request_type_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `pronoun` (`pronoun`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclresources
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('1', '/electronic', 'S', '电子产品', 'electronic', '4', '2', null, null);
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2', '/food', 'S', '食物', 'food', '4', '2', null, null);
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('3', '/food/queryFood', 'R', '检索食物', 'queryFood', '2', '2', null, null);
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('4', '/category', 'M', '商品类别', 'category', null, null, null, null);
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('5', '/food/addFood', 'R', '新增食物', 'addFood', '2', '1', null, null);

-- ----------------------------
-- Table structure for `tbl_sysmgr_aclrole`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclrole`;
CREATE TABLE `tbl_sysmgr_aclrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(200) NOT NULL COMMENT '角色ID',
  `pronoun` varchar(200) NOT NULL COMMENT '角色代名词',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_pronoun` (`role_name`,`pronoun`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclrole
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('1', '管理员', 'admin', '2016-07-13 15:49:57', '2016-07-13 15:49:59');
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('2', '正式用户', 'user', '2016-07-13 15:49:52', '2016-07-13 15:49:54');
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('3', '买家', 'buyer', '2016-07-13 15:55:31', '2016-07-13 15:55:33');
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('4', '卖家', 'seller', '2016-07-13 15:56:06', '2016-07-13 15:56:09');

-- ----------------------------
-- Table structure for `tbl_sysmgr_aclroleresources`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclroleresources`;
CREATE TABLE `tbl_sysmgr_aclroleresources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resource_ids` varchar(255) NOT NULL COMMENT '资源ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleid_resourceid` (`role_id`,`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclroleresources
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('1', '1', '1,3', '2016-07-13 15:35:25', '2016-07-13 15:35:29');
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('2', '2', '3,4', null, null);
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('3', '3', '4,5', null, null);

-- ----------------------------
-- Table structure for `tbl_sysmgr_acluser`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_acluser`;
CREATE TABLE `tbl_sysmgr_acluser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(50) NOT NULL COMMENT '密码',
  `role_ids` varchar(50) NOT NULL COMMENT '角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_acluser
-- ----------------------------
INSERT INTO `tbl_sysmgr_acluser` VALUES ('1', 'test', '1', '1,2,3,4', null, null);

-- ----------------------------
-- Table structure for `tbl_sysset_systemconfig`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysset_systemconfig`;
CREATE TABLE `tbl_sysset_systemconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `key_code` varchar(200) NOT NULL COMMENT '配置编码',
  `key_group` varchar(200) NOT NULL COMMENT '分组',
  `key_name` varchar(200) NOT NULL COMMENT '显示名字',
  `key_value` varchar(200) NOT NULL COMMENT '值',
  `comment` varchar(200) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysset_systemconfig
-- ----------------------------
INSERT INTO `tbl_sysset_systemconfig` VALUES ('1', '2016-09-22 00:00:00', '2016-09-22 00:00:00', '1', '1', '1', '1', '1');
INSERT INTO `tbl_sysset_systemconfig` VALUES ('2', '2016-09-23 17:35:18', '2016-09-23 17:35:22', '2', '2', '2', '2', '2');
