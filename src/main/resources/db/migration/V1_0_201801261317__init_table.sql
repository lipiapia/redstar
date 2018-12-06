/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-02-28 12:46:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `b_region_code` varchar(255) DEFAULT NULL,
  `s_region_code` varchar(255) DEFAULT NULL,
  `mall_code` varchar(255) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `enum_value` enum('') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
CREATE TABLE `t_admin_role` (
  `admin_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_card_group
-- ----------------------------
CREATE TABLE `t_card_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mall_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `merchant_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vip_open_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `bind_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `unbind_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_fans
-- ----------------------------
CREATE TABLE `t_fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open_id` int(32) DEFAULT NULL,
  `type` tinyint(10) DEFAULT NULL,
  `avatar_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` tinyint(10) DEFAULT NULL,
  `city` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `language` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `union_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vip_open_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `members` int(11) DEFAULT NULL,
  `mall_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `management` varchar(24) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `reception_count` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_group_fans
-- ----------------------------
CREATE TABLE `t_group_fans` (
  `group_id` int(11) DEFAULT NULL,
  `fans_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_mall
-- ----------------------------
CREATE TABLE `t_mall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mall_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open_hours` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `b_region_code` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_region_code` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `s_region_code` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `province` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mall_type` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mall_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ps_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oms_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `floor_num` int(11) DEFAULT NULL,
  `mall_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `b_region_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `s_region_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mall_type_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_media
-- ----------------------------
CREATE TABLE `t_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size` int(11) NOT NULL,
  `type` tinyint(32) NOT NULL,
  `url` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_media_tag
-- ----------------------------
CREATE TABLE `t_media_tag` (
  `media_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_merchant
-- ----------------------------
CREATE TABLE `t_merchant` (
  `id` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `brand_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `management` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `building_id` varchar(23) COLLATE utf8_unicode_ci DEFAULT NULL,
  `building_name` varchar(34) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_sort` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `coupon_desc` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `coupon_flag` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `floor_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `floor_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hot_flag` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `is_marking` int(8) DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `main_category_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market_sort` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `online_status` int(11) DEFAULT NULL,
  `promotion_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `promotion_start_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_style` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_tel` varchar(26) COLLATE utf8_unicode_ci DEFAULT NULL,
  `show_tags` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tag_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_score` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vitual_category_id` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_time_in_millis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `address` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
CREATE TABLE `t_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stored_card
-- ----------------------------
CREATE TABLE `t_stored_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `coupons_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_vip
-- ----------------------------
CREATE TABLE `t_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fans_open_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vip_open_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_channel` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_mall` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `first_login_channel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_login_mall` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
