CREATE TABLE `customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `name` VARCHAR(255) DEFAULT  NULL COMMENT'用户名称',
 `contact` VARCHAR(255) DEFAULT NULL COMMENT '联系人',
 `telephone` VARCHAR(255) DEFAULT NULL COMMENT '电话号码',
 `email` VARCHAR(255) DEFAULT NULL COMMENT '电子邮件',
 `remark` text DEFAULT NULL COMMENT '备注',
 PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO `customer` VALUES ('1', 'customer1', 'Jack', '13918535169', 'Jackey@gmail.com', null);

INSERT INTO `customer` VALUES ('2', 'customer2', 'Rose', '13918535179', 'Rose@gmail.com', null);