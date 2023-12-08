/*
Navicat MySQL Data Transfer

Source Server         : 139.159.204.251
Source Server Version : 50651
Source Host           : 139.159.204.251:13306
Source Database       : working-order

Target Server Type    : MYSQL
Target Server Version : 50651
File Encoding         : 65001

Date: 2023-12-08 15:47:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(32) NOT NULL,
  `order_no` varchar(64) NOT NULL,
  `order_amount` decimal(16,2) DEFAULT NULL,
  `order_status` tinyint(11) NOT NULL,
  `order_pay_channel` tinyint(11) NOT NULL,
  `status` tinyint(11) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
