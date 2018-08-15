DROP DATABASE IF EXISTS `news`;
CREATE SCHEMA `news` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;

CREATE TABLE `news`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '用户登录密码',
  `createtime` DATE NULL COMMENT '创建时间',
  `updatetime` DATE NULL COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`),
  INDEX `login` (`username` ASC, `password` ASC, `status` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '用户表';

CREATE TABLE `news`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` CHAR(50) NOT NULL DEFAULT '(未分类)' COMMENT '分类名',
  `createtime` DATE NULL COMMENT '创建时间',
  `updatetime` DATE NULL COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`),
  INDEX `name` (`name` ASC, `status` ASC, `createtime` ASC, `updatetime` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '新闻分类表';

CREATE TABLE `news`.`news` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` VARCHAR(45) NOT NULL COMMENT '新闻标题',
  `summary` VARCHAR(512) NULL COMMENT '新闻摘要',
  `content` VARCHAR(4096) NULL COMMENT '新闻内容',
  `category_id` INT NULL COMMENT '新闻分类id',
  `user_id` INT NULL COMMENT '作者(用户)id',
  `createtime` DATE NULL COMMENT '创建时间',
  `updatetime` DATE NULL COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`, `status`),
  INDEX `user` (`user_id` ASC, `status` ASC),
  INDEX `category` (`category_id` ASC, `status` ASC),
  INDEX `time` (`createtime` ASC, `updatetime` ASC, `status` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '新闻表';

CREATE TABLE `news`.`comment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` VARCHAR(512) NULL COMMENT '评论内容',
  `ip` CHAR(15) NULL COMMENT '发表评论的ip地址',
  `news_id` INT UNSIGNED NOT NULL COMMENT '对应的新闻id',
  `user_id` VARCHAR(45) NULL COMMENT '发表评论的用户id',
  `createtime` DATE NULL COMMENT '评论创建时间',
  `updatetime` DATE NULL COMMENT '评论修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0正常,负数异常)',
  PRIMARY KEY (`id`),
  INDEX `newsid` (`news_id` ASC),
  INDEX `userid` (`user_id` ASC),
  INDEX `date` (`createtime` ASC, `updatetime` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '评论表';

