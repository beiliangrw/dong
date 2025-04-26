/*
 Navicat Premium Data Transfer

 Source Server         : zyd
 Source Server Type    : MySQL
 Source Server Version : 80041
 Source Host           : localhost:3306
 Source Schema         : a_wx_shop

 Target Server Type    : MySQL
 Target Server Version : 80041
 File Encoding         : 65001

 Date: 17/04/2025 20:30:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS `address_info`;
CREATE TABLE `address_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '收货人',
  `phone` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `uid` int NOT NULL COMMENT '用户',
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_info
-- ----------------------------
INSERT INTO `address_info` VALUES (19, '1', '111', '111111', 23, '2021-10-31 01:00:25');
INSERT INTO `address_info` VALUES (21, '张三', '31131', '12313131', 34, '2021-10-31 22:40:34');
INSERT INTO `address_info` VALUES (26, '张三', '13123456789', '安徽省合肥市政务区湖畔春天6栋1506室', 34, '2022-04-29 00:22:42');
INSERT INTO `address_info` VALUES (27, '老王', '18876256732', '上海市浦东新区东方明珠405', 1, '2022-04-29 03:35:04');
INSERT INTO `address_info` VALUES (28, '5', '18888888888', '01', 2, '2025-04-17 19:54:18');
INSERT INTO `address_info` VALUES (29, '', '', '', 2, '2025-04-16 21:46:06');

-- ----------------------------
-- Table structure for cart_info
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `count` int NOT NULL DEFAULT 0 COMMENT '数量',
  `goodsId` bigint NOT NULL DEFAULT 0 COMMENT '所属商品',
  `userId` bigint NOT NULL DEFAULT 0 COMMENT '所属用户',
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '购物车信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart_info
-- ----------------------------
INSERT INTO `cart_info` VALUES (23, 1, 4, 34, '2022-04-29 01:20:05');

-- ----------------------------
-- Table structure for collect_info
-- ----------------------------
DROP TABLE IF EXISTS `collect_info`;
CREATE TABLE `collect_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  `foreignId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collect_info
-- ----------------------------
INSERT INTO `collect_info` VALUES (1, 23, 3, 1);
INSERT INTO `collect_info` VALUES (6, 23, 3, 2);
INSERT INTO `collect_info` VALUES (7, 30, 3, 1);
INSERT INTO `collect_info` VALUES (8, 32, 3, 2);

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评价内容',
  `goodsId` bigint NOT NULL DEFAULT 0 COMMENT '所属商品',
  `userId` bigint NOT NULL DEFAULT 0 COMMENT '评价人id',
  `level` int NULL DEFAULT NULL COMMENT '用户等级',
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品评价表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_info
-- ----------------------------
INSERT INTO `comment_info` VALUES (2, '这个是真的不错！', 4, 2, NULL, '2022-04-29 18:16:59');

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '商品描述',
  `price` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `discount` double(10, 2) NULL DEFAULT 1.00 COMMENT '商品折扣',
  `sales` int NOT NULL DEFAULT 0 COMMENT '商品销量',
  `hot` int NOT NULL DEFAULT 0 COMMENT '商品点赞数',
  `recommend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '否' COMMENT '是否是推荐',
  `count` int NOT NULL DEFAULT 0 COMMENT '商品库存',
  `typeId` bigint NOT NULL DEFAULT 0 COMMENT '所属类别',
  `fileIds` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片id，用英文逗号隔开',
  `userId` bigint NOT NULL DEFAULT 0 COMMENT '评价人id',
  `level` int NULL DEFAULT NULL COMMENT '用户等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品详情表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES (1, '百威', '', 8.90, 1.00, 46, 16777216, '是', 998, 2, '[50]', 1, NULL);
INSERT INTO `goods_info` VALUES (2, '花生米', '', 15.00, 1.00, 24, 524288, '是', 998, 4, '[51]', 1, NULL);
INSERT INTO `goods_info` VALUES (3, '科罗娜', '', 9.90, 1.00, 34, 32768, '是', 997, 2, '[52]', 1, NULL);
INSERT INTO `goods_info` VALUES (4, '轩尼诗VSOP', '', 680.00, 1.00, 1, 0, '是', 998, 3, '[53]', 1, NULL);
INSERT INTO `goods_info` VALUES (6, '人头马VSOP', '', 588.00, 1.00, 1, 0, '是', 999, 3, '[54]', 1, NULL);
INSERT INTO `goods_info` VALUES (7, '奥尔良烤翅', '', 28.00, 1.00, 1, 0, '是', 998, 4, '[55]', 1, NULL);
INSERT INTO `goods_info` VALUES (8, '莫吉托', '', 25.00, 1.00, 1, 0, '是', 998, 5, '[56]', 1, NULL);
INSERT INTO `goods_info` VALUES (9, '扑克', '', 2.00, 1.00, 0, 0, '是', 999, 6, '[58]', 1, NULL);
INSERT INTO `goods_info` VALUES (10, '可乐', '', 6.00, 1.00, 0, 0, '是', 999, 7, '[59]', 1, NULL);
INSERT INTO `goods_info` VALUES (11, '24瓶百威便宜套餐', '', 199.00, 1.00, 2, 0, '是', 997, 1, '[60]', 1, NULL);

-- ----------------------------
-- Table structure for nx_system_file_info
-- ----------------------------
DROP TABLE IF EXISTS `nx_system_file_info`;
CREATE TABLE `nx_system_file_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `originName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存储文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nx_system_file_info
-- ----------------------------
INSERT INTO `nx_system_file_info` VALUES (12, 'iphone12.jpg', 'iphone121606791180182.jpg');
INSERT INTO `nx_system_file_info` VALUES (13, '牛仔外套.jpg', '牛仔外套1606791664516.jpg');
INSERT INTO `nx_system_file_info` VALUES (14, '女装.jpg', '女装1606791689058.jpg');
INSERT INTO `nx_system_file_info` VALUES (15, '华为Meta30.jpg', '华为Meta301606791754239.jpg');
INSERT INTO `nx_system_file_info` VALUES (16, '30950.jpg', '309501608444761955.jpg');
INSERT INTO `nx_system_file_info` VALUES (17, 'u=2669303306,338188050&fm=26&gp=0.jpg', 'u=2669303306,338188050&fm=26&gp=01608454963850.jpg');
INSERT INTO `nx_system_file_info` VALUES (18, 'u=1646342622,2222263206&fm=26&gp=0.jpg', 'u=1646342622,2222263206&fm=26&gp=01608454991319.jpg');
INSERT INTO `nx_system_file_info` VALUES (19, 'timg.jpg', 'timg1608455054164.jpg');
INSERT INTO `nx_system_file_info` VALUES (20, '1799.gif', '17991608457607178.gif');
INSERT INTO `nx_system_file_info` VALUES (21, 'v2-530577b3ffa1485ff1ca4ff7c16d360a_720w.jpg', 'v2-530577b3ffa1485ff1ca4ff7c16d360a_720w1616596274944.jpg');
INSERT INTO `nx_system_file_info` VALUES (22, 'ftq.png', 'ftq1616596454387.png');
INSERT INTO `nx_system_file_info` VALUES (23, '1.jpg', '11635692145594.jpg');
INSERT INTO `nx_system_file_info` VALUES (24, '1.jpg', '11635692618307.jpg');
INSERT INTO `nx_system_file_info` VALUES (25, 'n1.jpg', 'n11651222062202.jpg');
INSERT INTO `nx_system_file_info` VALUES (26, 'n2.jpg', 'n21651222224452.jpg');
INSERT INTO `nx_system_file_info` VALUES (27, '1.jpg', '11651222224452.jpg');
INSERT INTO `nx_system_file_info` VALUES (28, 'n1.jpg', 'n11651222224454.jpg');
INSERT INTO `nx_system_file_info` VALUES (29, 'n1.jpg', 'n11651222472457.jpg');
INSERT INTO `nx_system_file_info` VALUES (30, '1.jpg', '11651222472455.jpg');
INSERT INTO `nx_system_file_info` VALUES (31, '2.jpg', '21651222472455.jpg');
INSERT INTO `nx_system_file_info` VALUES (32, '2.jpg', '21651223228935.jpg');
INSERT INTO `nx_system_file_info` VALUES (33, '3.jpg', '31651223228938.jpg');
INSERT INTO `nx_system_file_info` VALUES (34, '1.jpg', '11651223228938.jpg');
INSERT INTO `nx_system_file_info` VALUES (35, '3.jpg', '31651223967308.jpg');
INSERT INTO `nx_system_file_info` VALUES (36, '1.jpg', '11651223967308.jpg');
INSERT INTO `nx_system_file_info` VALUES (37, '2.jpg', '21651223967309.jpg');
INSERT INTO `nx_system_file_info` VALUES (38, '3.jpg', '31651225014658.jpg');
INSERT INTO `nx_system_file_info` VALUES (39, '1.jpg', '11651225014656.jpg');
INSERT INTO `nx_system_file_info` VALUES (40, '2.jpg', '21651225302388.jpg');
INSERT INTO `nx_system_file_info` VALUES (41, '1.jpg', '11651225302391.jpg');
INSERT INTO `nx_system_file_info` VALUES (42, '1.jpg', '11651225652307.jpg');
INSERT INTO `nx_system_file_info` VALUES (43, '2.jpg', '21651225652307.jpg');
INSERT INTO `nx_system_file_info` VALUES (44, '2.jpg', '21651225865788.jpg');
INSERT INTO `nx_system_file_info` VALUES (45, '1.jpg', '11651225865788.jpg');
INSERT INTO `nx_system_file_info` VALUES (46, '1.jpg', '11651225896635.jpg');
INSERT INTO `nx_system_file_info` VALUES (47, '3.jpg', '31651227025801.jpg');
INSERT INTO `nx_system_file_info` VALUES (48, '2.jpg', '21651227025801.jpg');
INSERT INTO `nx_system_file_info` VALUES (49, '1.jpg', '11651227025801.jpg');
INSERT INTO `nx_system_file_info` VALUES (50, 'baiwei.jpg', 'baiwei1744783847870.jpg');
INSERT INTO `nx_system_file_info` VALUES (51, '花生米.jpg', '花生米1744784030457.jpg');
INSERT INTO `nx_system_file_info` VALUES (52, '科罗娜.jpg', '科罗娜1744784079984.jpg');
INSERT INTO `nx_system_file_info` VALUES (53, '轩尼诗VSOP.jpg', '轩尼诗VSOP1744785001800.jpg');
INSERT INTO `nx_system_file_info` VALUES (54, '人头马VSOP.jpg', '人头马VSOP1744785101318.jpg');
INSERT INTO `nx_system_file_info` VALUES (55, '奥尔良烤翅.jpg', '奥尔良烤翅1744785226848.jpg');
INSERT INTO `nx_system_file_info` VALUES (56, '莫吉托.jpg', '莫吉托1744785337060.jpg');
INSERT INTO `nx_system_file_info` VALUES (57, '纸巾.jpg', '纸巾1744785852505.jpg');
INSERT INTO `nx_system_file_info` VALUES (58, '扑克.jpg', '扑克1744785911602.jpg');
INSERT INTO `nx_system_file_info` VALUES (59, '可乐.jpg', '可乐1744785950800.jpg');
INSERT INTO `nx_system_file_info` VALUES (60, '百威套餐.jpg', '百威套餐1744786033704.jpg');

-- ----------------------------
-- Table structure for order_goods_rel
-- ----------------------------
DROP TABLE IF EXISTS `order_goods_rel`;
CREATE TABLE `order_goods_rel`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `orderId` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `goodsId` bigint NOT NULL DEFAULT 0 COMMENT '所属商品',
  `count` int NULL DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单商品关系映射表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_goods_rel
-- ----------------------------
INSERT INTO `order_goods_rel` VALUES (1, 1, 1, 30);
INSERT INTO `order_goods_rel` VALUES (2, 2, 2, 10);
INSERT INTO `order_goods_rel` VALUES (3, 3, 3, 20);
INSERT INTO `order_goods_rel` VALUES (8, 6, 1, 2);
INSERT INTO `order_goods_rel` VALUES (9, 7, 3, 2);
INSERT INTO `order_goods_rel` VALUES (10, 8, 3, 2);
INSERT INTO `order_goods_rel` VALUES (11, 9, 1, 1);
INSERT INTO `order_goods_rel` VALUES (12, 10, 1, 1);
INSERT INTO `order_goods_rel` VALUES (13, 10, 2, 1);
INSERT INTO `order_goods_rel` VALUES (14, 10, 5, 1);
INSERT INTO `order_goods_rel` VALUES (15, 11, 3, 1);
INSERT INTO `order_goods_rel` VALUES (16, 12, 2, 1);
INSERT INTO `order_goods_rel` VALUES (17, 13, 3, 2);
INSERT INTO `order_goods_rel` VALUES (18, 14, 2, 1);
INSERT INTO `order_goods_rel` VALUES (19, 15, 2, 1);
INSERT INTO `order_goods_rel` VALUES (20, 16, 2, 1);
INSERT INTO `order_goods_rel` VALUES (21, 17, 2, 1);
INSERT INTO `order_goods_rel` VALUES (22, 18, 3, 1);
INSERT INTO `order_goods_rel` VALUES (23, 19, 1, 1);
INSERT INTO `order_goods_rel` VALUES (24, 20, 2, 1);
INSERT INTO `order_goods_rel` VALUES (27, 23, 2, 1);
INSERT INTO `order_goods_rel` VALUES (28, 23, 1, 1);
INSERT INTO `order_goods_rel` VALUES (29, 24, 2, 1);
INSERT INTO `order_goods_rel` VALUES (30, 24, 1, 1);
INSERT INTO `order_goods_rel` VALUES (35, 29, 1, 1);
INSERT INTO `order_goods_rel` VALUES (36, 30, 1, 1);
INSERT INTO `order_goods_rel` VALUES (39, 33, 8, 1);
INSERT INTO `order_goods_rel` VALUES (40, 34, 4, 1);
INSERT INTO `order_goods_rel` VALUES (41, 35, 3, 2);
INSERT INTO `order_goods_rel` VALUES (42, 36, 1, 1);
INSERT INTO `order_goods_rel` VALUES (43, 37, 7, 1);
INSERT INTO `order_goods_rel` VALUES (44, 38, 11, 1);
INSERT INTO `order_goods_rel` VALUES (45, 39, 2, 1);
INSERT INTO `order_goods_rel` VALUES (46, 40, 11, 1);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `orderId` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `totalPrice` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总价格',
  `userId` bigint NOT NULL DEFAULT 0 COMMENT '所属用户',
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '未发货' COMMENT '订单状态',
  `addressId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品订单信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (33, 22022042916573524, 9.81, 2, '2022-04-29 16:57:47', '已退款', 28);
INSERT INTO `order_info` VALUES (34, 22022042918148561, 1169.10, 2, '2022-04-29 18:14:37', '已完成', 29);
INSERT INTO `order_info` VALUES (35, 22025041615152123, 19.80, 2, '2025-04-16 15:15:37', '待收货', 28);
INSERT INTO `order_info` VALUES (36, 22025041621288467, 8.90, 2, '2025-04-16 21:28:17', '待发货', 29);
INSERT INTO `order_info` VALUES (37, 22025041713555861, 28.00, 2, '2025-04-17 13:55:50', '已取消', 28);
INSERT INTO `order_info` VALUES (38, 22025041716297396, 199.00, 2, '2025-04-17 16:29:48', '待收货', 28);
INSERT INTO `order_info` VALUES (39, 22025041719549579, 15.00, 2, '2025-04-17 19:54:25', '待发货', 28);
INSERT INTO `order_info` VALUES (40, 22025041719549807, 199.00, 2, '2025-04-17 19:54:25', '待付款', 28);

-- ----------------------------
-- Table structure for type_info
-- ----------------------------
DROP TABLE IF EXISTS `type_info`;
CREATE TABLE `type_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '类型名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '类型描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type_info
-- ----------------------------
INSERT INTO `type_info` VALUES (1, '优惠套餐', '');
INSERT INTO `type_info` VALUES (2, '啤酒', '');
INSERT INTO `type_info` VALUES (3, '洋酒', '');
INSERT INTO `type_info` VALUES (4, '小吃', '');
INSERT INTO `type_info` VALUES (5, '鸡尾酒', '');
INSERT INTO `type_info` VALUES (6, '服务', '');
INSERT INTO `type_info` VALUES (7, '饮料', '');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `cardId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证',
  `level` int NOT NULL DEFAULT 1 COMMENT '权限等级',
  `account` double(10, 2) NULL DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 21, NULL, '15279768119', '江西省', NULL, NULL, NULL, 1, 12.80);
INSERT INTO `user_info` VALUES (2, '张三', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, 23, NULL, '18888888888', '安徽省合肥市蜀山区锦绣花园405', NULL, NULL, NULL, 3, 10188.20);
INSERT INTO `user_info` VALUES (3, '李四', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, 23, NULL, '18912345678', '上海市浦东新区外滩18号', NULL, NULL, NULL, 3, 0.00);

SET FOREIGN_KEY_CHECKS = 1;
