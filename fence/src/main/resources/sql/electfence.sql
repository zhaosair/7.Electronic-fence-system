/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : electfence

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 04/06/2021 14:32:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for archor
-- ----------------------------
DROP TABLE IF EXISTS `archor`;
CREATE TABLE `archor`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_sec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of archor
-- ----------------------------
INSERT INTO `archor` VALUES (2, NULL, '12642872.419999994', '2835947.113889749', '1', 9);
INSERT INTO `archor` VALUES (3, NULL, '12643560.419999992', '2833691.113921337', '2', 9);
INSERT INTO `archor` VALUES (4, NULL, '12645480.419999994', '2834907.1139044086', '3', 9);
INSERT INTO `archor` VALUES (5, NULL, '12645400.419999992', '2834955.1139037353', '4', 9);
INSERT INTO `archor` VALUES (48, NULL, '12642600.419999992', '2835755.1138924677', '1', 23);
INSERT INTO `archor` VALUES (49, NULL, '12644424.419999994', '2833755.1139204516', '2', 23);
INSERT INTO `archor` VALUES (50, NULL, '12641640.419999994', '2833755.1139204516', '3', 23);
INSERT INTO `archor` VALUES (51, NULL, '12645064.419999992', '2835339.1138983397', '4', 23);
INSERT INTO `archor` VALUES (52, NULL, '12642088.419999992', '2834779.113906202', '5', 23);
INSERT INTO `archor` VALUES (53, NULL, '12642184.419999992', '2835931.113889976', '1', 24);

-- ----------------------------
-- Table structure for deptment
-- ----------------------------
DROP TABLE IF EXISTS `deptment`;
CREATE TABLE `deptment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `regoin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deptment
-- ----------------------------
INSERT INTO `deptment` VALUES (1, '外勤一部', '1', 1);
INSERT INTO `deptment` VALUES (2, '办公室', '1', 1);
INSERT INTO `deptment` VALUES (3, '财务部', '1', 1);
INSERT INTO `deptment` VALUES (4, '人事部', '2', 1);
INSERT INTO `deptment` VALUES (5, '开发部', '2', 1);
INSERT INTO `deptment` VALUES (6, '实施一部', '2', 1);
INSERT INTO `deptment` VALUES (7, '实施二部', '2', 1);
INSERT INTO `deptment` VALUES (8, '企划部', NULL, 1);
INSERT INTO `deptment` VALUES (10, '答辩', NULL, 1);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL,
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `creatime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `color` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES (1, '区域一', 0, '#333fff', 0);
INSERT INTO `region` VALUES (13, '答辩', 0, NULL, 0);
INSERT INTO `region` VALUES (15, 'dddddd', 0, NULL, 0);
INSERT INTO `region` VALUES (23, '答辩2', 0, NULL, 0);
INSERT INTO `region` VALUES (24, '答辩3', 1, '1370', 0);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  `creatime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `startime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `finishtime` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `startlng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `startlat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `endlng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `endlat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '外勤一', 1, 1, NULL, '2021-04-23 14:33:50', '2021-04-25 02:34:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `task` VALUES (2, '2', 1, 1, NULL, '2021-04-22 08:08:15', '2021-04-29 08:04:24', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `task` VALUES (3, 'test', 2, 2, NULL, '2021-04-28 16:00:13', '2021-04-29 16:00:13', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `task` VALUES (6, '答辩', 3, 23, NULL, '2021-05-22 16:00:05', '2021-05-29 19:09:27', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `logintime` datetime(0) NULL DEFAULT NULL,
  `logoutime` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `dpid` int(0) NULL DEFAULT NULL,
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '李四', '123', '1', '1234578910', '2021-04-06 12:13:41', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES (3, 'test2', 'wangwu', NULL, '1', NULL, '2021-04-13 16:00:00', NULL, NULL, NULL, 2, NULL, NULL);
INSERT INTO `user` VALUES (11, 'aaa1', 'aaa1', NULL, '1', NULL, '2021-05-28 16:00:00', NULL, NULL, NULL, 3, NULL, NULL);
INSERT INTO `user` VALUES (12, 'sss1', 'sss1', NULL, '1', NULL, '2021-05-27 16:00:00', NULL, NULL, NULL, 4, NULL, NULL);

-- ----------------------------
-- Table structure for warnlog
-- ----------------------------
DROP TABLE IF EXISTS `warnlog`;
CREATE TABLE `warnlog`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `tid` int(0) NULL DEFAULT NULL,
  `uid` int(0) NULL DEFAULT NULL,
  `errortime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
