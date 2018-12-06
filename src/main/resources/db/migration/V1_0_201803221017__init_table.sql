/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-22 10:17:08
*/

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_sms_setting` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `c_limit_count` int(13) NOT NULL,
  `b_limit_count` int(13) NOT NULL,
  `c_content` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
