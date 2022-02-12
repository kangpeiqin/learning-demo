DROP TABLE IF EXISTS product;
-- 商品库存表 --
CREATE TABLE `product` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
    `num` int(11) NOT NULL COMMENT '库存',
    `sale` int(11) NOT NULL COMMENT '已售',
    `version` int(11) NOT NULL COMMENT '乐观锁，版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS order_detail;
-- 订单详情表 --
CREATE TABLE `order_detail` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `prod_id` int(11) NOT NULL COMMENT '商品id',
    `name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;