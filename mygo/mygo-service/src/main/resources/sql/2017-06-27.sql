ALTER TABLE t_goods ADD COLUMN goods_index SMALLINT(1) NOT NULL COMMENT '商品索引，0：待创建，1：已创建' DEFAULT 0;