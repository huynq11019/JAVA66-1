/*
 Navicat Premium Data Transfer

 Source Server         : mylocal
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : newjava

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 12/08/2021 09:21:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_authrority
-- ----------------------------
DROP TABLE IF EXISTS `account_authrority`;
CREATE TABLE `account_authrority`  (
  `user_id` int NOT NULL,
  `authrority_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `authrority_name`) USING BTREE,
  INDEX `FK7hmtpbm6yl425mfbrvw1v58yw`(`authrority_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_authrority
-- ----------------------------
INSERT INTO `account_authrority` VALUES (1, 'ADMIN');
INSERT INTO `account_authrority` VALUES (1, 'EMPLOYEE');
INSERT INTO `account_authrority` VALUES (1, 'GUEST');
INSERT INTO `account_authrority` VALUES (1, 'LESI');
INSERT INTO `account_authrority` VALUES (1, 'USER');
INSERT INTO `account_authrority` VALUES (2, 'USER');
INSERT INTO `account_authrority` VALUES (3, 'USER');
INSERT INTO `account_authrority` VALUES (4, 'USER');

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `dob` datetime(0) NULL DEFAULT NULL,
  `descrption` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fullname` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passowrd_slat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_n7ihswpy07ci568w34q0oi8he`(`email`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, '2021-07-16 21:30:02', NULL, NULL, '2021-07-16 21:30:02', NULL, '2001-12-03 11:53:49', 'không phải huy', 'lala@gmail.com', 'Nguyễn Quang Huy', NULL, '$2a$10$YO0J5g/FWjKskvYjWsV88OdC1GZEKcQQ7iSBW0p/K3qWqy3JXJNjq', '0925573154', 0);
INSERT INTO `accounts` VALUES (2, '2021-08-06 13:53:28', NULL, NULL, '2021-08-06 13:53:28', NULL, '2001-12-03 11:53:49', 'không phải huy', 'chutich@gmail.com', 'đỗ quang huy', NULL, '$2a$10$/qfCkzU0XPv01axpK3.qte7kAU/82fPjc238XKL6Ycv7Bf4W2HKUW', '0925573154', 0);
INSERT INTO `accounts` VALUES (3, '2021-08-07 19:52:43', NULL, NULL, '2021-08-07 19:52:43', NULL, '2001-08-18 00:00:00', '', 'huynqph11019@gmail.com', 'huy đẹp trai', NULL, '$2a$10$/pXshidEmkHZXZa/oHqy1eQVwElv8jhsC0lYraxVph0TO79/P//bu', '09255731541', 0);
INSERT INTO `accounts` VALUES (4, '2021-08-08 00:49:07', NULL, NULL, '2021-08-08 00:49:07', NULL, '2001-08-18 00:00:00', '', 'huyenle@gmaill.c0m', 'chào em anh đứng đây từ chiều', NULL, '$2a$10$IWWrz4ORKL4mFfhMCxOFrO7JnRdeytBNgr1OVTGhzrgeVY0qh0.9e', '93784949401', 0);

-- ----------------------------
-- Table structure for authoritys
-- ----------------------------
DROP TABLE IF EXISTS `authoritys`;
CREATE TABLE `authoritys`  (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authoritys
-- ----------------------------
INSERT INTO `authoritys` VALUES ('ADMIN');
INSERT INTO `authoritys` VALUES ('EMPLOYEE');
INSERT INTO `authoritys` VALUES ('GUEST');
INSERT INTO `authoritys` VALUES ('LESI');
INSERT INTO `authoritys` VALUES ('USER');

-- ----------------------------
-- Table structure for caregories
-- ----------------------------
DROP TABLE IF EXISTS `caregories`;
CREATE TABLE `caregories`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `descripion` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_cate` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of caregories
-- ----------------------------
INSERT INTO `caregories` VALUES (1, '2021-07-17 10:56:24', NULL, NULL, NULL, NULL, 'Sản phẩm không bá', 'Phụ kiện', NULL);
INSERT INTO `caregories` VALUES (3, '2021-07-20 23:45:54', NULL, NULL, NULL, NULL, 'p?n', 'Giày', NULL);
INSERT INTO `caregories` VALUES (4, '2021-07-23 14:54:51', NULL, NULL, NULL, NULL, NULL, 'Áo sơ my', NULL);
INSERT INTO `caregories` VALUES (5, '2021-07-23 14:54:46', NULL, NULL, NULL, NULL, NULL, 'áo T-shirt', NULL);
INSERT INTO `caregories` VALUES (6, '2021-07-23 14:54:41', NULL, NULL, NULL, NULL, NULL, 'Áo Long-Tee', NULL);

-- ----------------------------
-- Table structure for oderdetails
-- ----------------------------
DROP TABLE IF EXISTS `oderdetails`;
CREATE TABLE `oderdetails`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `form_od` int NULL DEFAULT NULL,
  `form_product` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK4rawyoa7npgi4bue9v72g8u5e`(`form_od`) USING BTREE,
  INDEX `FK6l5bky0iflc443w6kixitxjv3`(`form_product`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = FIXED;

-- ----------------------------
-- Records of oderdetails
-- ----------------------------
INSERT INTO `oderdetails` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for orderdetails
-- ----------------------------
DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE `orderdetails`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `form_od` int NULL DEFAULT NULL,
  `form_product` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK7fvt4d5lcjkpf49al4t3pplic`(`form_od`) USING BTREE,
  INDEX `FKs17nwmg1i91g87lh3bjj2s650`(`form_product`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of orderdetails
-- ----------------------------
INSERT INTO `orderdetails` VALUES (1, '2021-08-05 22:06:14', 1, '2021-08-05 22:06:22', '2021-08-05 22:06:28', 1, 1, 2, 1, 1);
INSERT INTO `orderdetails` VALUES (2, '2021-08-05 22:06:14', 1, '2021-08-05 22:06:22', '2021-08-05 22:06:28', 1, 20, 1, 1, 2);
INSERT INTO `orderdetails` VALUES (3, '2021-08-05 22:09:46', 1, '2021-08-05 22:09:51', '2021-08-05 22:09:54', 1, 1, 3, 1, 5);
INSERT INTO `orderdetails` VALUES (16, '2021-08-08 20:11:43', NULL, NULL, '2021-08-08 20:11:43', NULL, 9999, 1, 13, 1);
INSERT INTO `orderdetails` VALUES (17, '2021-08-08 20:11:43', NULL, NULL, '2021-08-08 20:11:43', NULL, 189.05, 1, 13, 2);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `ship_adress` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paymethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `od_account` int NULL DEFAULT NULL,
  `sdt` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nguoinhan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8n67gug99erdehb586dfbb9nv`(`od_account`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '2021-08-05 22:05:01', 1, '2021-08-05 22:05:05', '2021-08-05 22:05:09', 1, 'trần duy hưng', '1', 1, 1, '092384247', 'nguyễn Huy');
INSERT INTO `orders` VALUES (13, '2021-08-08 20:11:43', NULL, NULL, '2021-08-08 20:11:43', NULL, '1cầu giấy hà nội việt nam', 'PAYPAL', 0, 3, '29384723947', 'huy đep trai chứ ai');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` int NULL DEFAULT NULL,
  `delete_at` datetime(0) NULL DEFAULT NULL,
  `last_updated` datetime(0) NULL DEFAULT NULL,
  `updated_by` int NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount` float NULL DEFAULT NULL,
  `image_product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_product` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantity` int NOT NULL,
  `real_price` float NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `product_category` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_sgw85sd2bk54y77onr4q2hkqr`(`name_product`) USING BTREE,
  INDEX `FKqyy58aotrofmdxvgin38flpat`(`product_category`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '2021-05-10 21:41:06', 1, '2021-07-16 21:41:10', '2021-07-16 21:41:14', 1, 'dành cho những bạn chưa có người yêu', 0, 'product-6.jpg', 'Huy dep trai', 1000, 9999, 1, 1);
INSERT INTO `products` VALUES (2, '2021-07-17 10:01:40', 1, NULL, '2021-07-17 10:01:40', NULL, 'sản phẩm này thật hữu ích', 5, 'product-7.jpg', 'Iphone 12 Product', 101, 199, 1, 3);
INSERT INTO `products` VALUES (5, '2021-07-17 10:26:07', 1, '2021-07-26 16:23:19', '2021-07-17 10:26:07', NULL, 'Sản phẩm cần thiết', 5, 'product-2.jpg', 'nguyenquang Huy', 101, 199, 3, 5);
INSERT INTO `products` VALUES (6, '2021-07-17 10:31:22', 1, NULL, '2021-07-17 10:31:22', NULL, '872187', 1.2, 'product-3.jpg', 'alahuaca', 101, 199, 3, 1);
INSERT INTO `products` VALUES (7, '2021-07-17 10:52:23', 1, NULL, '2021-07-17 10:52:23', NULL, 'bao tải', 10, 'product-4.jpg', 'nhuy pro ', 101, 201, 3, 4);
INSERT INTO `products` VALUES (8, '2021-07-17 10:55:48', 1, NULL, '2021-07-17 10:55:48', NULL, 'đây là phần comment', 21, 'product-5.jpg', 'găng tay', 1, 111, 1, 5);
INSERT INTO `products` VALUES (10, '2021-07-30 22:38:20', 1, NULL, '2021-07-30 22:38:20', 0, 'string', 0, 'product-2.jpg', 'string', 1, 0, 1, NULL);
INSERT INTO `products` VALUES (11, '2021-07-30 22:38:20', 1, NULL, '2021-07-30 22:38:20', 0, 'string', 0, 'product-3.jpg', 'đối tượng mới', 2, 0, 1, NULL);
INSERT INTO `products` VALUES (12, '2021-07-30 22:38:20', 1, NULL, '2021-07-30 22:38:20', 0, 'string', 0, 'product-4.jpg', 'đối tượng mới22 ', 3, 0, 1, NULL);
INSERT INTO `products` VALUES (13, '2021-07-30 22:38:20', 1, NULL, '2021-07-30 22:38:20', 0, 'string', 0, 'product-5.jpg', 'đây thực sự là một đối tượng mới ', 4, 0, 1, NULL);
INSERT INTO `products` VALUES (14, '2021-07-30 22:38:20', 1, '2021-07-30 22:38:20', '2021-07-30 22:38:20', 0, 'string', 0, 'product-2.jpg', 'đây thực sự là một đối tượn111g mới ', 5, 0, 1, NULL);
INSERT INTO `products` VALUES (21, '2021-08-03 00:46:19', 1, NULL, '2021-08-06 01:02:54', 1, 'ôi bạn ơi ', 1, '138469158_890555418152259_2890737087517986142_n.jpg', 'Áo này đẹp lắm', 100, 99, 1, NULL);
INSERT INTO `products` VALUES (22, '2021-08-03 00:50:20', 1, NULL, '2021-08-06 01:02:20', 1, '12313123123123123', 0, '182605627_212566887341897_6506222090673047661_n.jpg', 'Thỏ 7 màu', 12313, 9109, 1, NULL);
INSERT INTO `products` VALUES (23, '2021-08-04 10:15:29', 1, NULL, '2021-08-04 10:15:29', NULL, '1231231231231231', 1, 'huycuoi.jpg', 'test tạo sản phẩm', 123, 99, 1, 5);
INSERT INTO `products` VALUES (24, '2021-08-05 20:19:00', 1, NULL, '2021-08-06 01:32:16', 1, 'chắc là tối thiểu 5 ký tự', 2, '151858681_1110579796059542_2122967250475157570_n.jpg', 'Prodcut s', 0, 99, 1, 5);

SET FOREIGN_KEY_CHECKS = 1;
