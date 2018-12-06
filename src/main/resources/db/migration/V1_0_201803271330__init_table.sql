/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-27 13:30:08
*/

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_employee_identity` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `start_change_time` timestamp NULL DEFAULT NULL,
  `end_change_time` timestamp NULL DEFAULT NULL,
  `employee_name` VARCHAR (255) NOT NULL,
  `employee_mobile` VARCHAR (255) NOT NULL,
  `shop_name` VARCHAR (255) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `open_id` VARCHAR (255) NOT NULL,
  `is_shop_owner` tinyint (0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
