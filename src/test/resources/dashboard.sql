-- 任务表，记录待完成的任务
-- DROP TABLE IF EXISTS task;
CREATE TABLE task
(
  id      VARCHAR(32) PRIMARY KEY NOT NULL
  COMMENT '唯一ID',
  title   VARCHAR(128)            NOT NULL
  COMMENT '标题',
  content VARCHAR(1024) COMMENT '内容',
  type    VARCHAR(8) COMMENT '类别',
  status  INT COMMENT '状态',
  url     VARCHAR(1024) COMMENT '相关URL',
  remarks VARCHAR(1024) COMMENT '备注'
);

--  命令总表，记录了命令的主要信息
DROP TABLE IF EXISTS command;
CREATE TABLE command
(
  id        VARCHAR(32) PRIMARY KEY NOT NULL
  COMMENT '唯一ID',
  name      VARCHAR(128)            NOT NULL
  COMMENT '命令名',
  content   VARCHAR(1024) COMMENT '内容：命令的详细说明',
  type      VARCHAR(8) COMMENT '类别：用于区分平台，windows，linux，mac等',
  parameter VARCHAR(1024) COMMENT '支持的参数',
  remarks   VARCHAR(1024) COMMENT '备注'
);

--  命令demo表，记录了单条命令的主使用范例
DROP TABLE IF EXISTS command_demo;
CREATE TABLE command_demo
(
  id        VARCHAR(32) PRIMARY KEY NOT NULL
  COMMENT '唯一ID',
  command_id      VARCHAR(128)            NOT NULL
  COMMENT '命令表的ID',
  content   VARCHAR(1024) COMMENT 'demo',
  description   VARCHAR(1024) COMMENT '内容：demo的详细说明',
  remarks   VARCHAR(1024) COMMENT '备注'
);

-- 命令名称不重复
ALTER TABLE command ADD UNIQUE (name);

-- DROP TABLE IF EXISTS hs_user;
CREATE TABLE user (
	id VARCHAR (32) PRIMARY KEY NOT NULL  COMMENT '唯一序号',
	name VARCHAR (32) NOT NULL COMMENT  '用户名'  ,
	sex VARCHAR (8) COMMENT   '性别',
	age NUMERIC (8) COMMENT   '年龄',
	amount NUMERIC (18, 4) COMMENT   '账户余额',
	desc1 VARCHAR(128) COMMENT '描述',
	lasttime VARCHAR (64) COMMENT   '时间',
	remarks VARCHAR (1024) COMMENT   '备注'
)   COMMENT   '用户表';