
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(11) DEFAULT NULL COMMENT '反馈用户',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `title` varchar(255) DEFAULT NULL COMMENT '反馈标题',
  `content` text COMMENT '反馈内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of feedback
-- ----------------------------
BEGIN;
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`) VALUES (6, '路人甲', '31952874@qq.com', '测试一号', '测试这个系统有问题吗？', '2022-05-03 16:13:59', '2022-05-03 16:13:59');
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`) VALUES (7, '路人乙', '31952874@qq.com', '测试二号', '惆怅长岑长错错错错错错', '2022-05-03 16:14:20', '2022-05-03 16:14:20');
COMMIT;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户搜索历史主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `keyword` varchar(255) DEFAULT NULL COMMENT '搜索关键字',
  `operate_type` int(1) DEFAULT NULL COMMENT '类型：1搜索，2科目，3药品',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of history
-- ----------------------------
BEGIN;
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (126, 4, '10,无', 1, '2022-05-03 16:09:34', '2022-05-03 16:09:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (127, 4, '10,无', 1, '2022-05-03 16:09:40', '2022-05-03 16:09:40');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (129, 4, '10,无', 1, '2022-05-03 16:09:52', '2022-05-03 16:09:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (132, 5, '7,无', 1, '2022-05-03 16:17:41', '2022-05-03 16:17:41');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (135, 5, '17,无', 1, '2022-05-03 16:18:22', '2022-05-03 16:18:22');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (145, 5, '7,无', 1, '2022-05-03 16:37:57', '2022-05-03 16:37:57');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (146, 5, '9,无', 1, '2022-05-03 16:38:34', '2022-05-03 16:38:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (147, 5, '9,无', 1, '2022-05-03 16:41:59', '2022-05-03 16:41:59');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (148, 5, '9,无', 1, '2022-05-03 16:42:14', '2022-05-03 16:42:14');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (149, 5, '9,无', 1, '2022-05-03 16:42:45', '2022-05-03 16:42:45');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (150, 5, '9,无', 1, '2022-05-03 16:43:54', '2022-05-03 16:43:54');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (151, 5, '9,无', 1, '2022-05-03 16:44:26', '2022-05-03 16:44:26');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (152, 5, '9,无', 1, '2022-05-03 16:44:45', '2022-05-03 16:44:45');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (153, 5, '2,无', 1, '2022-05-03 16:44:51', '2022-05-03 16:44:51');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (154, 5, '2,无', 1, '2022-05-03 16:45:46', '2022-05-03 16:45:46');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (155, 5, '1', 3, '2022-05-07 15:34:34', '2022-05-07 15:34:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (157, 4, '9,无', 1, '2022-07-14 19:32:52', '2022-07-14 19:32:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (158, 4, '1,无', 1, '2022-07-14 19:32:56', '2022-07-14 19:32:56');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`) VALUES (159, 4, '17,无', 1, '2022-07-14 19:32:59', '2022-07-14 19:32:59');
COMMIT;



-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_account` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户的真实名字',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `user_sex` varchar(1) DEFAULT NULL COMMENT '用户性别',
  `user_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `user_tel` varchar(50) DEFAULT NULL COMMENT '手机号',
  `role_status` int(11) DEFAULT NULL COMMENT '角色状态，1管理员，0普通用户',
  `img_path` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`, `role_status`, `img_path`, `create_time`, `update_time`) VALUES (4, 'admin', '管理员', '123456', 23, '男', '2678788262@qq.com', '17746678954', 1, 'https://moti-cloud-v2.oss-cn-beijing.aliyuncs.com/Snipaste_2022-05-01_15-37-01.png', '2022-05-03 15:55:41', '2022-05-03 15:56:15');
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`, `role_status`, `img_path`, `create_time`, `update_time`) VALUES (5, 'zhangsan', '张三', '123456', 23, '女', 'isxuewei@qq.com', '17879544343', 0, 'https://su-share.oss-cn-beijing.aliyuncs.com/5/5dc107dcd2db4cbd8ad561f4c1642886.png', '2022-05-03 16:15:53', '2022-05-03 16:17:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
