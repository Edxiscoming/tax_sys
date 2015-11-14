/*
Navicat MySQL Data Transfer

Source Server         : fc
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : itcasttax

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-11-14 11:06:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `complain`
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain` (
  `comp_id` varchar(32) NOT NULL,
  `comp_company` varchar(100) DEFAULT NULL,
  `comp_name` varchar(20) DEFAULT NULL,
  `comp_mobile` varchar(20) DEFAULT NULL,
  `is_NM` tinyint(1) DEFAULT NULL,
  `comp_time` datetime DEFAULT NULL,
  `comp_title` varchar(200) NOT NULL,
  `to_comp_name` varchar(20) DEFAULT NULL,
  `to_comp_dept` varchar(100) DEFAULT NULL,
  `comp_content` text,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES ('1', '部门A', '帅哥', '13888888888', '0', '2015-11-25 09:50:39', '行贿', '管理员', '部门A', '投诉内容1', '0');
INSERT INTO `complain` VALUES ('2', '部门A', '美女', '19555555555', '1', '2015-10-08 09:51:48', '贪污', '管理员', '部门A', '投诉内容2', '0');
INSERT INTO `complain` VALUES ('3', '部门B', '小孩', '15555555555', '1', '2015-10-15 11:10:20', '投诉信息3', '管理员', '部门A', '投诉内容3', '1');
INSERT INTO `complain` VALUES ('8aef06a850fb1c1f0150fb1cf3ec0000', '部门A', '超人', '5645212', '0', '2015-11-12 17:53:10', '投诉标题2222', 'edison', '部门A', '<p>坏人。。。。</p>', '0');
INSERT INTO `complain` VALUES ('8aef06a85103b600015103b76e990001', '部门A', '超人', '5645212', '0', '2015-11-14 09:58:51', '', null, '', '<p><img src=\"http://localhost:8080/tax_sys/upload/ueditor/image/20151114/1447466329838087653.jpg\" title=\"1447466329838087653.jpg\" alt=\"D58A1B83C4A8081EA3B8F74091F507D1.jpg\"/></p>', '0');

-- ----------------------------
-- Table structure for `complain_reply`
-- ----------------------------
DROP TABLE IF EXISTS `complain_reply`;
CREATE TABLE `complain_reply` (
  `reply_id` varchar(32) NOT NULL,
  `comp_id` varchar(32) NOT NULL,
  `replyer` varchar(20) DEFAULT NULL,
  `reply_dept` varchar(100) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `reply_content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK_comp_reply` (`comp_id`),
  CONSTRAINT `FK_comp_reply` FOREIGN KEY (`comp_id`) REFERENCES `complain` (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain_reply
-- ----------------------------
INSERT INTO `complain_reply` VALUES ('8aef06a850fa47040150fa48f3d00000', '3', '超人', '部门A', '2015-11-12 14:01:36', '韩国锦湖更具根据');
INSERT INTO `complain_reply` VALUES ('8aef06a850fa47040150fa5cd87c0001', '3', '超人', '部门A', '2015-11-12 14:23:20', '22222222');
INSERT INTO `complain_reply` VALUES ('8aef06a850fa61530150fa61dc790000', '3', '超人', '部门A', '2015-11-12 14:28:48', '33333');
INSERT INTO `complain_reply` VALUES ('8aef06a850fa61530150fa61f17e0001', '3', '超人', '部门A', '2015-11-12 14:28:54', '44444444');
INSERT INTO `complain_reply` VALUES ('8aef06a85103b600015103b7386b0000', '8aef06a850fb1c1f0150fb1cf3ec0000', '超人', '部门A', '2015-11-14 09:58:37', '');

-- ----------------------------
-- Table structure for `info`
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `info_id` varchar(32) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` longtext,
  `memo` varchar(200) DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('8aef06a850ef44720150ef45611a0000', 'tzgg', 'dasd', 'dasdas', '<p><strong>sdasdgdgdgd</strong><strong><span style=\"text-decoration: underline;\">dfdfdf<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\"/></span></strong></p>', 'sadasdasda', '超人', '2015-11-10 10:41:45', '1');
INSERT INTO `info` VALUES ('8aef06a850efd5ac0150efd61b680000', 'tzgg', '', '', '<p><img src=\"http://localhost:8080/tax_sys/upload/ueditor/image/20151110/1447132795449037340.jpg\" title=\"1447132795449037340.jpg\" alt=\"968494DCAB90AB9E193472F4E058A271.jpg\"/></p>', '', '超人', '2015-11-10 13:19:50', '1');
INSERT INTO `info` VALUES ('8aef06a850f11e1e0150f11ee6fa0000', 'tzgg', '', '我我爱爱你', '', '', '超人', '2015-11-10 19:18:56', '1');
INSERT INTO `info` VALUES ('8aef06a850f45e7f0150f4603dd90000', 'tzgg', '32', 'gfdgdfg', '<p>fdgdfg</p>', 'fdgdf', '超人', '2015-11-11 10:29:10', '1');
INSERT INTO `info` VALUES ('8aef06a850f45e7f0150f4605de20001', 'tzgg', 'gdfgdf', 'dfgdfgdf', '<p>fdgdf</p>', 'fdgdfg', '超人', '2015-11-11 10:29:23', '1');
INSERT INTO `info` VALUES ('8aef06a850f45e7f0150f460742d0002', 'tzgg', 'fdgdfg', 'gdgdf', '<p>dgdfgdf</p>', '', '超人', '2015-11-11 10:29:29', '1');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('8aef06a850da54880150da56183d0000', '管理员', '1');
INSERT INTO `role` VALUES ('8aef06a850db51400150db52bb9d0000', '一般用户', '1');
INSERT INTO `role` VALUES ('8aef06a850db51400150db52ed040001', '蹭客', '1');
INSERT INTO `role` VALUES ('8aef06a850ef425e0150ef4345fe0000', '超级管理员', '1');

-- ----------------------------
-- Table structure for `role_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `role_id` varchar(32) NOT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`code`),
  KEY `FK45FBD6285F14EAEC` (`role_id`),
  CONSTRAINT `FK45FBD6285F14EAEC` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('8aef06a850da54880150da56183d0000', 'spaces');
INSERT INTO `role_privilege` VALUES ('8aef06a850da54880150da56183d0000', 'zxxx');
INSERT INTO `role_privilege` VALUES ('8aef06a850db51400150db52bb9d0000', 'spaces');
INSERT INTO `role_privilege` VALUES ('8aef06a850db51400150db52bb9d0000', 'zxxx');
INSERT INTO `role_privilege` VALUES ('8aef06a850db51400150db52ed040001', 'xzgl');
INSERT INTO `role_privilege` VALUES ('8aef06a850ef425e0150ef4345fe0000', 'hqfw');
INSERT INTO `role_privilege` VALUES ('8aef06a850ef425e0150ef4345fe0000', 'nsfw');
INSERT INTO `role_privilege` VALUES ('8aef06a850ef425e0150ef4345fe0000', 'spaces');
INSERT INTO `role_privilege` VALUES ('8aef06a850ef425e0150ef4345fe0000', 'xzgl');
INSERT INTO `role_privilege` VALUES ('8aef06a850ef425e0150ef4345fe0000', 'zxxx');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `dept` varchar(20) NOT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `headImg` varchar(100) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8aef06a850cd4cc50150cd504f8e0000', 'kobe1111', '部门A', 'kobe', '123', null, '', 'dadasd@qq.com', '1555555555555', '2015-11-18 00:00:00', '1', 'dadadasd');
INSERT INTO `user` VALUES ('8aef06a850d1972b0150d19818680000', 'edison', '部门A', 'edx', '123', null, '', '123456@qq.com', '15708496415', '2015-11-09 00:00:00', '1', '123');
INSERT INTO `user` VALUES ('8aef06a850d261910150d26504240000', 'dsadas', '部门A', 'sadas', 'dasdas', 'user/', '', 'dasdasdsa', 'sadasdda', '2015-11-18 00:00:00', '1', 'asdasd');
INSERT INTO `user` VALUES ('8aef06a850d268a40150d26933f70000', 'toux', '部门A', 'dasdas', 'hjgh', null, '', 'jghjg', 'ghjgh', '2015-11-18 00:00:00', '1', 'jghjghjg');
INSERT INTO `user` VALUES ('8aef06a850d268a40150d26d80fb0001', '66646', '部门A', '2131321', '21312', null, '', '2131213', '321213', '2015-11-25 00:00:00', '1', 'dsadas');
INSERT INTO `user` VALUES ('8aef06a850d268a40150d26e72500002', 'yuytuty', '部门A', 'tyuty', 'tyuty', 'user/bf54cbdf88ad4e12a48beb3f0f7c91db.jpg', '', 'tyut', 'tyuty', '2015-11-28 00:00:00', '1', 'yutyuty');
INSERT INTO `user` VALUES ('8aef06a850d5a0430150d5a1546c0000', '用户1test', '部门A', 'user3', '123456', null, '', 'user1@itcast.com', '13888888888', '1999-10-10 00:00:00', '1', null);
INSERT INTO `user` VALUES ('8aef06a850d5a0430150d5a154c90001', 'test', '部门B', 'user4', '123456', null, '', 'user2@itcast.com', '13888888888', '1999-10-11 00:00:00', '1', '');
INSERT INTO `user` VALUES ('8aef06a850db8f3e0150db901df60000', '大大', '部门A', 'fdsfsdf', 'dfsdfsd', null, '', 'sdfsdf', 'sdfsd', '2015-11-17 00:00:00', '1', 'fsdfs');
INSERT INTO `user` VALUES ('8aef06a850dbb8010150dbbac2320000', 'dsadasd', '部门A', 'gfdgdfgdf', 'fdgfd', null, '', 'gfdgdf', 'fdgdf', '2015-11-10 00:00:00', '1', 'gdfg');
INSERT INTO `user` VALUES ('8aef06a850ef425e0150ef43d4280001', '超人', '部门A', 'man', '123', null, '', 'dasdasd@qq.com', '5645212', '2015-11-13 00:00:00', '1', 'dasdada');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK143BF46A5F14EAEC` (`role_id`),
  CONSTRAINT `FK143BF46A5F14EAEC` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('8aef06a850da54880150da56183d0000', '8aef06a850db8f3e0150db901df60000');
INSERT INTO `user_role` VALUES ('8aef06a850da54880150da56183d0000', '8aef06a850dbb8010150dbbac2320000');
INSERT INTO `user_role` VALUES ('8aef06a850db51400150db52bb9d0000', '8aef06a850d5a0430150d5a154c90001');
INSERT INTO `user_role` VALUES ('8aef06a850db51400150db52bb9d0000', '8aef06a850db8f3e0150db901df60000');
INSERT INTO `user_role` VALUES ('8aef06a850db51400150db52ed040001', '8aef06a850db8f3e0150db901df60000');
INSERT INTO `user_role` VALUES ('8aef06a850ef425e0150ef4345fe0000', '8aef06a850ef425e0150ef43d4280001');
