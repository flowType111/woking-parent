/*
Navicat MySQL Data Transfer

Source Server         : 139.159.204.251
Source Server Version : 50651
Source Host           : 139.159.204.251:13306
Source Database       : working-common

Target Server Type    : MYSQL
Target Server Version : 50651
File Encoding         : 65001

Date: 2023-12-13 19:05:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_interface_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_interface_auth`;
CREATE TABLE `tb_interface_auth` (
  `id` varchar(32) NOT NULL,
  `access_key` varchar(50) NOT NULL,
  `encryption_key` varchar(200) NOT NULL,
  `enable` tinyint(11) NOT NULL DEFAULT '1',
  `status` tinyint(11) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_user` varchar(50) NOT NULL,
  `update_user` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_interface_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_interface_config`;
CREATE TABLE `tb_interface_config` (
  `id` varchar(32) NOT NULL,
  `interface_auth_id` varchar(32) NOT NULL,
  `open_interface_id` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_open_interface
-- ----------------------------
DROP TABLE IF EXISTS `tb_open_interface`;
CREATE TABLE `tb_open_interface` (
  `id` varchar(32) NOT NULL,
  `interface_code` varchar(100) NOT NULL,
  `interface_name` varchar(100) NOT NULL,
  `interface_describe` varchar(200) NOT NULL,
  `status` tinyint(11) NOT NULL,
  `create_tiem` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_user` varchar(50) NOT NULL,
  `update_user` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
