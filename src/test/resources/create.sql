/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : news

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2018-08-19 16:28:19
*/

DROP DATABASE IF EXISTS `news`;
CREATE SCHEMA `news` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `news`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父类ID',
  `name` char(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '(未分类)' COMMENT '分类名',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`,`parent_id`),
  KEY `name` (`name`,`status`,`createtime`,`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='新闻分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 0, '国际', '2018-8-19 16:09:04', '2018-8-19 16:09:04', 0);
INSERT INTO `category` VALUES (2, 0, '军事', '2018-8-19 16:09:04', '2018-8-19 16:09:04', 0);
INSERT INTO `category` VALUES (3, 0, '体育', '2018-8-19 16:09:04', '2018-8-19 16:09:04', 0);
INSERT INTO `category` VALUES (4, 0, '娱乐', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (5, 0, '社会', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (6, 0, '财经', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (7, 0, '科技', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (8, 0, '健康', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (9, 0, '汽车', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (10, 0, '教育', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (11, 0, '房产', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (12, 0, '家居', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (13, 0, '旅游', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (14, 0, '文化', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);
INSERT INTO `category` VALUES (15, 0, '其他', '2018-8-19 16:09:05', '2018-8-19 16:09:05', 0);

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评论内容',
  `ip` char(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发表评论的ip地址',
  `news_id` int(10) unsigned NOT NULL COMMENT '对应的新闻id',
  `user_id` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发表评论的用户id',
  `createtime` datetime DEFAULT NULL COMMENT '评论创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '评论修改时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`),
  KEY `newsid` (`news_id`),
  KEY `userid` (`user_id`),
  KEY `DATETIME` (`createtime`,`updatetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(45) NOT NULL COMMENT '新闻标题',
  `summary` varchar(512) DEFAULT NULL COMMENT '新闻摘要',
  `content` varchar(4096) DEFAULT NULL COMMENT '新闻内容',
  `category_id` int(11) DEFAULT NULL COMMENT '新闻分类id',
  `user_id` int(11) DEFAULT NULL COMMENT '作者(用户)id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`,`status`),
  KEY `user` (`user_id`,`status`),
  KEY `category` (`category_id`,`status`),
  KEY `time` (`createtime`,`updatetime`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻表';

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '用户登录密码',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否为管理员',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `login` (`username`,`password`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '2018-8-19 16:24:56', '2018-8-19 16:24:56', 1, 0);
