-- 字段：唯一ID,标题，详情
DROP TABLE IF EXISTS task;
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