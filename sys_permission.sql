/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : working-user

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 15/12/2023 11:18:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `component_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(0) NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `sort_no` double(8, 2) NULL DEFAULT NULL COMMENT '菜单排序',
  `always_show` tinyint(1) NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点:    1是0否',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden` int(0) NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
  `hide_tab` int(0) NULL DEFAULT NULL COMMENT '是否隐藏tab: 0否,1是',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int(0) NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
  `status` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
  `internal_or_external` tinyint(1) NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_menu_type`(`menu_type`) USING BTREE,
  INDEX `index_menu_hidden`(`hidden`) USING BTREE,
  INDEX `index_menu_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1170592628746878978', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '菜单管理', '/system/menu', 'system/menu/index', 1, NULL, NULL, 1, NULL, '1', 0.00, 0, 'ant-design:menu-fold-outlined', 0, 0, 0, 0, NULL, 'admin', '2019-09-08 15:00:05', 'ceshi', '2023-12-13 20:22:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1214376304951664642', '3f915b2769fc80648e92d04e84ca059d', '用户编辑', '', '', 0, NULL, NULL, 2, 'system:user:edit', '1', 1.00, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2020-01-07 10:40:47', 'admin', '2022-11-17 16:24:33', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1214462306546319362', '3f915b2769fc80648e92d04e84ca059d', '新增用户', '', '', 0, NULL, NULL, 2, 'system:user:add', '1', 1.00, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2020-01-07 16:22:32', 'admin', '2022-11-17 16:24:47', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1588513553652436993', '3f915b2769fc80648e92d04e84ca059d', '修改密码', NULL, NULL, 0, NULL, NULL, 2, 'system:user:changepwd', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-04 20:48:39', 'admin', '2022-11-04 20:49:06', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592112984361365505', '1170592628746878978', '添加菜单', NULL, NULL, 0, NULL, NULL, 2, 'system:permission:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:11:30', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592114574275211265', '3f915b2769fc80648e92d04e84ca059d', '删除用户', NULL, NULL, 0, NULL, NULL, 2, 'system:user:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:17:49', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592114652566089729', '3f915b2769fc80648e92d04e84ca059d', '批量删除用户', NULL, NULL, 0, NULL, NULL, 2, 'system:user:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:18:08', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592116663936184322', '1170592628746878978', '编辑菜单', NULL, NULL, 0, NULL, NULL, 2, 'system:permission:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:26:07', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592118604640645122', '1170592628746878978', '删除菜单', NULL, NULL, 0, NULL, NULL, 2, 'system:permission:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:33:50', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592120323667750914', '190c2b43bec6a5f7a4194a85db67d96a', '角色添加', NULL, NULL, 0, NULL, NULL, 2, 'system:role:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:40:40', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592120372296511490', '190c2b43bec6a5f7a4194a85db67d96a', '角色编辑', NULL, NULL, 0, NULL, NULL, 2, 'system:role:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:40:52', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1592120427007012865', '190c2b43bec6a5f7a4194a85db67d96a', '角色删除', NULL, NULL, 0, NULL, NULL, 2, 'system:role:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-11-14 19:41:05', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1693195557097164801', '190c2b43bec6a5f7a4194a85db67d96a', '查询所有角色', NULL, NULL, 0, NULL, NULL, 2, 'system:role:list', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-20 17:37:34', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('190c2b43bec6a5f7a4194a85db67d96a', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '角色管理', '/system/role', 'system/role/index', 1, NULL, NULL, 1, NULL, NULL, 2.00, 0, 'ant-design:solution', 0, 1, 0, NULL, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2021-09-17 15:58:00', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('3f915b2769fc80648e92d04e84ca059d', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '用户管理', '/system/user', 'system/user/index', 1, NULL, NULL, 1, NULL, NULL, 1.00, 0, 'ant-design:user', 0, 1, 0, NULL, NULL, NULL, '2018-12-25 20:34:38', 'sunjianlei', '2021-05-08 09:57:31', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('d7d6e2e4e2934f2c9385a623fd98c6f3', '', '系统管理', '/isystem', 'layouts/RouteView', 1, NULL, NULL, 0, NULL, NULL, 3.00, 0, 'ant-design:setting', 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-08-25 13:35:21', 0, 0, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
