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
-- alter table t_stored_card add COLUMN package_count int(11);
-- alter table t_stored_card add COLUMN package_money int(11);
alter table t_group_fans add COLUMN employee_id INT;
alter table t_group_fans add COLUMN join_time TIMESTAMP;
-- alter table t_stored_card CHANGE coupons_id coupons_package_id int(11);


