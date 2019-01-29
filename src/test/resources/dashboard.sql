-- 任务表，记录待完成的任务
-- DROP TABLE IF EXISTS task;
CREATE TABLE task (
	id VARCHAR (32) PRIMARY KEY NOT NULL COMMENT '唯一ID',
	title VARCHAR (128) NOT NULL COMMENT '标题',
	content VARCHAR (1024) COMMENT '内容',
	type VARCHAR (8) COMMENT '类别',
	STATUS INT COMMENT '状态',
	url VARCHAR (1024) COMMENT '相关URL',
	remarks VARCHAR (1024) COMMENT '备注'
);

--  命令总表，记录了命令的主要信息
-- DROP TABLE IF EXISTS command;
CREATE TABLE command (
	id VARCHAR (32) PRIMARY KEY NOT NULL COMMENT '唯一ID',
	NAME VARCHAR (128) NOT NULL COMMENT '命令名',
	content VARCHAR (1024) COMMENT '内容：命令的详细说明',
	type VARCHAR (8) COMMENT '类别：用于区分平台，windows，linux，mac等',
	parameter VARCHAR (1024) COMMENT '支持的参数',
	remarks VARCHAR (1024) COMMENT '备注'
);

--  命令表，记录了命令的使用范例
-- DROP TABLE IF EXISTS statement;
CREATE TABLE statement (
	id VARCHAR (32) PRIMARY KEY NOT NULL COMMENT '唯一ID',
	content VARCHAR (1024) COMMENT '内容' NOT NULL,
	description VARCHAR (1024) COMMENT '的详细说明',
	hot int COMMENT '热度',
	remarks VARCHAR (1024) COMMENT '备注'
);

-- 命令名称不重复
ALTER TABLE command ADD UNIQUE (NAME);

DROP TABLE IF EXISTS user;
CREATE TABLE USER (
	id VARCHAR (32) PRIMARY KEY NOT NULL COMMENT '唯一序号',
	name VARCHAR (1024) COMMENT '名字',
	password VARCHAR (1024) COMMENT '密码',
	roles VARCHAR (32) NOT NULL COMMENT '角色',
	introduction VARCHAR (1024) COMMENT '介绍',
	avatar VARCHAR (1024) COMMENT 'avatar',
	remarks VARCHAR (1024) COMMENT '备注'
) COMMENT '用户表';

