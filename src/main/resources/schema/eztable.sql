DROP DATABASE IF EXISTS `eztable`;

CREATE DATABASE `eztable`;

USE eztable;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(32) NOT NULL ,
  `user_pwd` VARCHAR(32) NOT NULL ,
  `user_level` INT NOT NULL DEFAULT 20 COMMENT '20:学生 10:老师',
  PRIMARY KEY (`user_id`)
) ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_id` INT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(128) NOT NULL,
  `user_id` INT NOT NULL ,
  `type_id` INT NOT NULL ,
  `file_description` VARCHAR(128) NOT NULL ,
  `file_viewed` INT NOT NULL DEFAULT 0,
  `create_time` VARCHAR(64) ,
  PRIMARY KEY (`file_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS `file_keyword`;
CREATE TABLE `file_keyword`(
  `keyword_id` INT NOT NULL AUTO_INCREMENT,
  `keyword_name` VARCHAR(64) NOT NULL ,
  `keyword_column` INT NOT NULL ,
  `file_id` INT NOT NULL ,
  `keyword_value` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`keyword_id`),
  KEY `idx_file_id` (`file_id`)
) ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;


DROP table  IF EXISTS `token`;
create table `token`(
  `rec_id` int(11) not null PRIMARY KEY AUTO_INCREMENT,
  `user_id` int not null,
  `token` varchar(255) not null,
  `create_time` varchar(255) not null,
  `expire_time` varchar(255) not null
)ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;