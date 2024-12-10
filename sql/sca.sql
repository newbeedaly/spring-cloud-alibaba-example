/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : sca

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 24/07/2024 16:11:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sca_cmm_commodity
-- ----------------------------
DROP TABLE IF EXISTS `sca_cmm_commodity`;
CREATE TABLE `sca_cmm_commodity`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sca_cmm_commodity
-- ----------------------------
INSERT INTO `sca_cmm_commodity` VALUES (1, '111', 10.00);

-- ----------------------------
-- Table structure for sca_cmm_commodity_stock
-- ----------------------------
DROP TABLE IF EXISTS `sca_cmm_commodity_stock`;
CREATE TABLE `sca_cmm_commodity_stock`  (
  `id` bigint NOT NULL,
  `commodity_id` bigint NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sca_cmm_commodity_stock
-- ----------------------------
INSERT INTO `sca_cmm_commodity_stock` VALUES (1, 1, 100);

SET FOREIGN_KEY_CHECKS = 1;
