/*
Navicat MySQL Data Transfer

Source Server         : 139.159.204.251
Source Server Version : 50651
Source Host           : 139.159.204.251:13306
Source Database       : working-user

Target Server Type    : MYSQL
Target Server Version : 50651
File Encoding         : 65001

Date: 2023-12-05 11:26:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` varchar(32) NOT NULL,
  `menu_name` varchar(100) NOT NULL,
  `menu_code` varchar(100) NOT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `node_type` tinyint(11) NOT NULL,
  `sort` tinyint(11) NOT NULL,
  `level` tinyint(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `status` tinyint(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('0a4f30aa07e44ee2aee00a68a8b0a781', '详情', 'system_manage_catalog:system_user_role_manage_menu:button_details', 'system_manage_catalog:system_user_role_manage_menu', 'b3d3551f2a0a436c84d7b0a288159a5f', '3', '0', '3', '2023-12-05 10:17:38', '2023-12-05 10:17:40', null, null, '1');
INSERT INTO `tb_menu` VALUES ('0b17bc1d79af43838d3692d8c4f7043c', '删除', 'system_manage_catalog:system_user_role_menu_manage_menu:button_delete', 'system_manage_catalog:system_user_role_menu_manage_menu', 'beb9e4cb28e74e358a07e73b850d4e45', '3', '0', '3', '2023-12-05 10:19:01', '2023-12-05 10:19:03', null, null, '1');
INSERT INTO `tb_menu` VALUES ('11139ad21edc469fa6052d4a44372c99', '新增', 'system_manage_catalog:system_user_role_manage_menu:button_add', 'system_manage_catalog:system_user_role_manage_menu', 'b3d3551f2a0a436c84d7b0a288159a5f', '3', '0', '3', '2023-12-05 10:14:59', '2023-12-05 10:15:01', null, null, '1');
INSERT INTO `tb_menu` VALUES ('2d40a7c549a949cf86cd0ccaca9899ad', '系统管理', 'system_manage_catalog', null, null, '1', '1', '1', '2023-12-05 09:56:29', '2023-12-05 09:56:31', null, null, '1');
INSERT INTO `tb_menu` VALUES ('312cb02475a04e21bc4956c7e3fef7c8', '详情查询', 'system_manage_catalog:system_user_manage_menu:button_details', 'system_manage_catalog:system_user_manage_menu', 'c9e2854c9c214f90a330591f9de6a721', '3', '0', '3', '2023-12-05 10:11:40', '2023-12-05 10:11:42', null, null, '1');
INSERT INTO `tb_menu` VALUES ('4403863ec9f34f45a16fe1809696bcb6', '列表查询', 'system_manage_catalog:system_user_manage_menu:button_select', 'system_manage_catalog:system_user_manage_menu', 'c9e2854c9c214f90a330591f9de6a721', '3', '0', '3', '2023-12-05 10:08:37', '2023-12-05 10:08:41', null, null, '1');
INSERT INTO `tb_menu` VALUES ('6daa50545b8545858b9ad181850be9d1', '删除', 'system_manage_catalog:system_user_manage_menu:button_delete', 'system_manage_catalog:system_user_manage_menu', 'c9e2854c9c214f90a330591f9de6a721', '3', '0', '3', '2023-12-05 10:07:01', '2023-12-05 10:07:05', null, null, '1');
INSERT INTO `tb_menu` VALUES ('9271217526e44a268706a9dfc3bfc713', '修改', 'system_manage_catalog:system_user_role_menu_manage_menu:button_update', 'system_manage_catalog:system_user_role_menu_manage_menu', 'beb9e4cb28e74e358a07e73b850d4e45', '3', '0', '3', '2023-12-05 10:19:38', '2023-12-05 10:19:40', null, null, '1');
INSERT INTO `tb_menu` VALUES ('9300f17cbaea450bb1eb7bd92d1d905b', '新增', 'system_manage_catalog:system_user_manage_menu:button_add', 'system_manage_catalog:system_user_manage_menu', 'c9e2854c9c214f90a330591f9de6a721', '3', '0', '3', '2023-12-05 10:06:21', '2023-12-05 10:06:23', null, null, '1');
INSERT INTO `tb_menu` VALUES ('a8fb86cd69dc4211b907cdb9798ae2a4', '列表查询', 'system_manage_catalog:system_user_role_manage_menu:button_select', 'system_manage_catalog:system_user_role_manage_menu', 'b3d3551f2a0a436c84d7b0a288159a5f', '3', '0', '3', '2023-12-05 10:16:54', '2023-12-05 10:16:56', null, null, '1');
INSERT INTO `tb_menu` VALUES ('ab3230a68e104d0cb06972972f3181b6', '更改', 'system_manage_catalog:system_user_manage_menu:button_update', 'system_manage_catalog:system_user_manage_menu', 'c9e2854c9c214f90a330591f9de6a721', '3', '0', '3', '2023-12-05 10:07:50', '2023-12-05 10:07:53', null, null, '1');
INSERT INTO `tb_menu` VALUES ('b3d3551f2a0a436c84d7b0a288159a5f', '用户角色管理', 'system_manage_catalog:system_user_role_manage_menu', 'system_manage_catalog', '2d40a7c549a949cf86cd0ccaca9899ad', '2', '2', '2', '2023-12-05 10:03:02', '2023-12-05 10:03:04', null, null, '1');
INSERT INTO `tb_menu` VALUES ('be4d2c502dbd44f5a818064cd079ddb5', '列表查询', 'system_manage_catalog:system_user_role_menu_manage_menu:button_select', 'system_manage_catalog:system_user_role_menu_manage_menu', 'beb9e4cb28e74e358a07e73b850d4e45', '3', '0', '3', '2023-12-05 10:20:22', '2023-12-05 10:20:25', null, null, '1');
INSERT INTO `tb_menu` VALUES ('beb9e4cb28e74e358a07e73b850d4e45', '用户角色权限点管理', 'system_manage_catalog:system_user_role_menu_manage_menu', 'system_manage_catalog', '2d40a7c549a949cf86cd0ccaca9899ad', '2', '3', '2', '2023-12-05 10:04:04', '2023-12-05 10:04:07', null, null, '1');
INSERT INTO `tb_menu` VALUES ('c9e2854c9c214f90a330591f9de6a721', '用户管理', 'system_manage_catalog:system_user_manage_menu', 'system_manage_catalog', '2d40a7c549a949cf86cd0ccaca9899ad', '2', '1', '2', '2023-12-05 10:02:11', '2023-12-05 10:02:14', null, null, '1');
INSERT INTO `tb_menu` VALUES ('e8aec10cee8f4fb0bb82e2e3b157498d', '删除', 'system_manage_catalog:system_user_role_manage_menu:button_delete', 'system_manage_catalog:system_user_role_manage_menu', 'b3d3551f2a0a436c84d7b0a288159a5f', '3', '0', '3', '2023-12-05 10:15:39', '2023-12-05 10:15:42', null, null, '1');
INSERT INTO `tb_menu` VALUES ('f2a2c0a53e884ea89835b2cab9d90488', '修改', 'system_manage_catalog:system_user_role_manage_menu:button_update', 'system_manage_catalog:system_user_role_manage_menu', 'b3d3551f2a0a436c84d7b0a288159a5f', '3', '0', '3', '2023-12-05 10:16:17', '2023-12-05 10:16:19', null, null, '1');
INSERT INTO `tb_menu` VALUES ('fe99330585b04377a41ed1a5a1218137', '新增', 'system_manage_catalog:system_user_role_menu_manage_menu:button_add', 'system_manage_catalog:system_user_role_menu_manage_menu', 'beb9e4cb28e74e358a07e73b850d4e45', '3', '0', '3', '2023-12-05 10:18:22', '2023-12-05 10:18:24', null, null, '1');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` varchar(32) NOT NULL,
  `role_code` varchar(100) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `state` tinyint(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('d4da659dcfb14f5bb9d3674898645fd7', 'administrators', '管理员', '2023-12-05 10:26:18', '2023-12-05 10:26:21', null, null, '1');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `menu_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('719e637dcdeb4af3a4da1659ad45b2e6', 'd4da659dcfb14f5bb9d3674898645fd7', '2d40a7c549a949cf86cd0ccaca9899ad');
INSERT INTO `tb_role_menu` VALUES ('4f36a00f79424c6db30355453b7adf94', 'd4da659dcfb14f5bb9d3674898645fd7', 'b3d3551f2a0a436c84d7b0a288159a5f');
INSERT INTO `tb_role_menu` VALUES ('b15884df242d47e7bbe0543180533d89', 'd4da659dcfb14f5bb9d3674898645fd7', 'beb9e4cb28e74e358a07e73b850d4e45');
INSERT INTO `tb_role_menu` VALUES ('f3fca7761be543f692c3086493bdeb46', 'd4da659dcfb14f5bb9d3674898645fd7', 'c9e2854c9c214f90a330591f9de6a721');
INSERT INTO `tb_role_menu` VALUES ('131ccdafca83428fa0efd24ca016a270', 'd4da659dcfb14f5bb9d3674898645fd7', '0a4f30aa07e44ee2aee00a68a8b0a781');
INSERT INTO `tb_role_menu` VALUES ('7c7fbb80f9ac42579caff34de7077b10', 'd4da659dcfb14f5bb9d3674898645fd7', '0b17bc1d79af43838d3692d8c4f7043c');
INSERT INTO `tb_role_menu` VALUES ('0a8aef1445db4963b89a5d268eec1e44', 'd4da659dcfb14f5bb9d3674898645fd7', '11139ad21edc469fa6052d4a44372c99');
INSERT INTO `tb_role_menu` VALUES ('6df0e16682414d0085509e2be8c8b970', 'd4da659dcfb14f5bb9d3674898645fd7', '312cb02475a04e21bc4956c7e3fef7c8');
INSERT INTO `tb_role_menu` VALUES ('34c2c588f2ba47baac8deff23a9f9bcd', 'd4da659dcfb14f5bb9d3674898645fd7', '4403863ec9f34f45a16fe1809696bcb6');
INSERT INTO `tb_role_menu` VALUES ('6ee4682fa8994765b136779df305bfaf', 'd4da659dcfb14f5bb9d3674898645fd7', '6daa50545b8545858b9ad181850be9d1');
INSERT INTO `tb_role_menu` VALUES ('f8bbd3d413fc411890c9b8953ef850e7', 'd4da659dcfb14f5bb9d3674898645fd7', '9271217526e44a268706a9dfc3bfc713');
INSERT INTO `tb_role_menu` VALUES ('2578d2857f3149faa4e8cc2db72c1ed8', 'd4da659dcfb14f5bb9d3674898645fd7', '9300f17cbaea450bb1eb7bd92d1d905b');
INSERT INTO `tb_role_menu` VALUES ('c517855533624e2fac465f5438ea6760', 'd4da659dcfb14f5bb9d3674898645fd7', 'a8fb86cd69dc4211b907cdb9798ae2a4');
INSERT INTO `tb_role_menu` VALUES ('8be73853fd8d4b5999a1cd486b92f9e3', 'd4da659dcfb14f5bb9d3674898645fd7', 'ab3230a68e104d0cb06972972f3181b6');
INSERT INTO `tb_role_menu` VALUES ('60143a36740e42cfb67c07df63505881', 'd4da659dcfb14f5bb9d3674898645fd7', 'be4d2c502dbd44f5a818064cd079ddb5');
INSERT INTO `tb_role_menu` VALUES ('06231bb54c444425aac86ddaf8a4e015', 'd4da659dcfb14f5bb9d3674898645fd7', 'e8aec10cee8f4fb0bb82e2e3b157498d');
INSERT INTO `tb_role_menu` VALUES ('2082ef7673934ce0a38874800a38f338', 'd4da659dcfb14f5bb9d3674898645fd7', 'f2a2c0a53e884ea89835b2cab9d90488');
INSERT INTO `tb_role_menu` VALUES ('721ea574e96f4940874f1ee669dd6f65', 'd4da659dcfb14f5bb9d3674898645fd7', 'fe99330585b04377a41ed1a5a1218137');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(32) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `status` tinyint(11) NOT NULL DEFAULT '1',
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('a21364c311631aba5c41f90d25361bec', 'test', 'K8gt2ylKPoP1pB2D8qwN3em4hdR5xjURlfM9uzUtf9uWpkK9w7AETBZCvGx2m+7qnRBxxeiF5Cj4YLrOe/9rpOkJ0rwGyvGjxSMWjPcMd2rSmg3vZKnYM6SgKVxh6LfTzbqRg5XMBhqIJMLjRUAHrOxbliK54+QeEtMRFVuntxA=', '赚百万', '1', null, null, '2023-12-05 10:48:03', '2023-12-05 10:48:05');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('8ae04c1612f5d-bd22698d1c9ff68b', 'a21364c311631aba5c41f90d25361bec', 'd4da659dcfb14f5bb9d3674898645fd7');
