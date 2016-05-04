CREATE TABLE `t_user` (
  `_id` BIGINT NOT NULL AUTO_INCREMENT,
  `id` CHAR(32) NOT NULL COMMENT '用户ID',
  `gender` CHAR(8) NOT NULL COMMENT '性别',
  `name` CHAR(16) NOT NULL COMMENT '用户名',
  `status` INT NOT NULL COMMENT '用户状态',
  `salt` CHAR(8) NOT NULL COMMENT '密码加密种子',
  `password` CHAR(32) NOT NULL COMMENT '加密后密码',
  `avatar` VARCHAR(64) NOT NULL COMMENT '用户头像',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `t_user_emails` (
  `_id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner` CHAR(32) NOT NULL COMMENT '用户ID',
  `default_email_domain` CHAR(16) COMMENT '默认邮箱域名',
  `default_email_name` CHAR(16) COMMENT '默认邮箱名',
  `email_count` INT NOT NULL COMMENT '邮箱数量',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE INDEX `idx_owner` (`owner` ASC));

CREATE TABLE `t_user_email` (
  `_id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner` CHAR(32) NOT NULL COMMENT '用户ID',
  `email_domain` CHAR(16) NOT NULL COMMENT '邮箱域名',
  `email_name` CHAR(16) NOT NULL COMMENT '邮箱名',
  `status` INT NOT NULL COMMENT '状态',
  `emails_id` BIGINT NOT NULL COMMENT '所属user emails id',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `idx_email` (`email_name`, `email_domain`),
  INDEX `idx_owner` (`owner` ASC));

CREATE TABLE `t_user_phones` (
  `_id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner` CHAR(32) NOT NULL COMMENT '用户ID',
  `default_phone_num` CHAR(16) COMMENT '默认手机号',
  `phone_count` INT NOT NULL COMMENT '邮箱数量',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE INDEX `idx_owner` (`owner` ASC));

CREATE TABLE `t_user_phone` (
  `_id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner` CHAR(32) NOT NULL COMMENT '用户ID',
  `phone_num` CHAR(16) NOT NULL COMMENT '手机号',
  `status` INT NOT NULL COMMENT '状态',
  `phones_id` BIGINT NOT NULL COMMENT '所属user phones id',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `idx_phone` (`phone_num`),
  INDEX `idx_owner` (`owner` ASC));