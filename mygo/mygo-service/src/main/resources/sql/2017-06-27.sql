ALTER TABLE t_goods ADD COLUMN goods_index SMALLINT(1) NOT NULL COMMENT '商品索引，0：待创建，1：已创建' DEFAULT 0;

ALTER TABLE t_goods  MODIFY COLUMN goods_index SMALLINT(1) COMMENT '0：待创建，1：待修改，2：待删除，3：已完成';