CREATE TABLE t_user(
   user_name VARCHAR(50) NOT NULL COMMENT '用户名',
   PASSWORD VARCHAR(50) NOT NULL COMMENT '密码',
   sex CHAR(1) NOT NULL COMMENT '性别(M:男，F:女)', 
   real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
   age INT(3) NOT NULL COMMENT '年龄',
   create_time INT(11) NOT NULL COMMENT '创建时间',
   update_time INT(11) NOT NULL COMMENT '更新时间',
   uid INT(10) NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (uid)
)ENGINE=INNODB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;


CREATE TABLE t_goods(
	gid INT(10) NOT NULL AUTO_INCREMENT,
	goods_name VARCHAR(50) NOT NULL COMMENT '商品名称',
	goods_code VARCHAR(20) NOT NULL UNIQUE COMMENT '商品编码',
	cid INT(10) NOT NULL COMMENT '分类id',
	goods_pic VARCHAR(200) NOT NULL COMMENT '商品图片',
	price DECIMAL(10,2) NOT NULL COMMENT '价格',
	STATUS CHAR(1) NOT NULL DEFAULT 'W' COMMENT '上下架状态:W:待上架 O：上架，F；下架',
	create_time INT(11) NOT NULL COMMENT '创建时间',
	update_time INT(11) NOT NULL COMMENT '更新时间',
	 PRIMARY KEY (gid)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `t_sku` (
  `sid` int(10) NOT NULL AUTO_INCREMENT,
  `gid` int(10) NOT NULL COMMENT '商品id',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
  `barcode` varchar(50) NOT NULL COMMENT '条码',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `create_time` int(11) NOT NULL COMMENT '创建时间',
  `update_time` int(11) NOT NULL COMMENT '更新时间',
  `sku_pic` varchar(200) NOT NULL COMMENT 'sku图片',
  `status` smallint(1) NOT NULL COMMENT 'sku状态（0:正常，1：删除）',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `barcode` (`barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

CREATE TABLE t_sku_attr(
  sid INT (10) NOT NULL COMMENT 'sku id',
  caid INT (10) NOT NULL COMMENT '属性id',
  attr_value VARCHAR(50) NOT NULL COMMENT '属性值'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE t_category_attr(
	caid INT (10) NOT NULL AUTO_INCREMENT,
	attr_name VARCHAR(20) NOT NULL COMMENT '属性名称',
	cid INT (10) NOT NULL COMMENT '所属分类id',
	PRIMARY KEY (caid)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE t_category(
   cid INT(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
   cname VARCHAR(50) NOT NULL COMMENT '分类名称',
   pid INT(10) NOT NULL DEFAULT 0 COMMENT '父分类id',
   STATUS SMALLINT(1) DEFAULT 0 COMMENT '状态 0：正常，1：作废',
   PRIMARY KEY (cid)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
