
ALTER TABLE t_order ADD COLUMN update_time INT(11) NOT NULL COMMENT '更新时间' DEFAULT 0;

ALTER TABLE t_order ADD COLUMN order_version INT(11) NOT NULL COMMENT '版本号（防止并发请求，更改状态的时候使用，其他情况暂不需要）' DEFAULT 0;

