/** 说明：删除 order_bottleneck_test 数据库，并重建它，然后创建三张表 */

drop database if exists order_bottleneck_test;
drop user if exists 'order_bottleneck_test'@'%';
create database order_bottleneck_test default character set utf8mb4 collate utf8mb4_general_ci;
use order_bottleneck_test;
create user 'order_bottleneck_test'@'%' identified by 'Wng7_fncoia__2er';
grant all privileges on order_bottleneck_test.* to 'order_bottleneck_test'@'%';
flush privileges;

/*
 Navicat Premium Data Transfer

 Source Server         : 笔记本0.102-order_bottleneck_test
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 192.168.0.102:3306
 Source Schema         : order_bottleneck_test

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 14/03/2024 21:44:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint NOT NULL AUTO_RANDOM COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存',
  `is_on` tinyint NOT NULL DEFAULT 1 COMMENT '是否上架',
  `first_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第一张图片',
  `intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详情',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `price`(`price`) USING BTREE,
  INDEX `stock`(`stock`) USING BTREE,
  INDEX `is_on`(`is_on`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` (`name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES ('锤子（smartisan ) 坚果 Pro 2S 6G+64GB 炫光蓝 全面屏双摄 全网通4G手机 双卡双待 游戏手机', 1998.00, 100001, 1, 'https://resource.smartisan.com/resource/25cc6e783a664fbdf83c3c34774a9826.png', 'https://resource.smartisan.com/resource/25cc6e783a664fbdf83c3c34774a9826.png', '2024-03-14 17:38:13');
INSERT INTO `goods` (`name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`)VALUES ('坚果 R2 浅黑色 8G+128G', 2699.00, 100001, 1, 'https://resource.smartisan.com/resource/4d9e7683b590cf4a6996d3b13136bcf8.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/4d9e7683b590cf4a6996d3b13136bcf8.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:22:53');
INSERT INTO `goods` (`name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`)VALUES ('Smartisan TNT go 有线版', 1199.00, 100001, 1, 'https://resource.smartisan.com/resource/2d68b229cc7cafbb1e74bc0f996fa57c.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/2d68b229cc7cafbb1e74bc0f996fa57c.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:24:35');
INSERT INTO `goods` (`name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`)VALUES ('Smartisan 手机支架', 39.00, 100001, 1, 'https://resource.smartisan.com/resource/8b0fe3117164dab7d91439b93dc112e0.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/8b0fe3117164dab7d91439b93dc112e0.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:25:52');
INSERT INTO `goods` (`name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`)VALUES ('Smartisan 智能手写笔', 499.00, 100001, 1, 'https://resource.smartisan.com/resource/c9a55fe8b5bc506fec60659aa2dcebe9.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/c9a55fe8b5bc506fec60659aa2dcebe9.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:26:32');
-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint NOT NULL AUTO_RANDOM COMMENT '主键',
  `sn` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `user_id` int NOT NULL COMMENT '用户ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '下单人姓名',
  `shipping_mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货地址中的手机号',
  `shipping_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货地址中的姓名',
  `shipping_detail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货地址',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `status` int NOT NULL COMMENT '状态：1-待支付，2-待发货，3-待收货，4-已完成，5-已取消',
  `add_time` datetime NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique-sn`(`sn`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `addTime`(`add_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` bigint NOT NULL AUTO_RANDOM COMMENT '主键',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `goods_count` int NOT NULL COMMENT '商品数量',
  `goods_price` decimal(8, 2) NOT NULL COMMENT '商品单价',
  `goods_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品图片',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '单商品总金额',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单项' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
