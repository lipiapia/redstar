/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-26 18:47:08
*/

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_mall_welcom` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `welcom_words` VARCHAR (255) NOT NULL,
  `jump_poster` int(13) NOT NULL,
  `type` int(13) NOT NULL,
  `welcom_start_time` timestamp NULL DEFAULT NULL,
  `welcom_end_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(13) NOT NULL,
  `mall_code` VARCHAR (255) NOT NULL,
  `poster_id` int(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
