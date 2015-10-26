/*Table structure for table `invalid_buy_time` */

CREATE TABLE `invalid_buy_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `week_day` smallint(1) DEFAULT '0',
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `inversed` tinyint(1) NOT NULL DEFAULT '0',
  `time_period_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `price_table` */

CREATE TABLE `price_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `standard_period_length` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `last_sold` timestamp NULL DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `product_validity_id` bigint(20) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `deleted` smallint(1) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `place_of_production` varchar(255) DEFAULT NULL,
  `gui_product_type` int(11) DEFAULT '0',
  `certificate_enabled` smallint(1) DEFAULT '0',
  `activated` tinyint(1) NOT NULL DEFAULT '1',
  `product_variant` int(11) DEFAULT '0',
  `price_table_id` bigint(20) DEFAULT NULL,
  `vat` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product` (`name`,`application_id`),
  KEY `application_id` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `product_property` */

CREATE TABLE `product_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` text,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `product_property_name` (`name`),
  KEY `Product_id_and_name` (`product_id`,`name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `product_validity` */

CREATE TABLE `product_validity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NULL DEFAULT NULL,
  `expire_time` timestamp NULL DEFAULT NULL,
  `start_delay_minutes` int(11) NOT NULL DEFAULT '0',
  `end_delay_minutes` int(11) DEFAULT NULL,
  `start_pattern` varchar(200) DEFAULT NULL,
  `expire_pattern` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `time_period` */

CREATE TABLE `time_period` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `week_day` smallint(1) DEFAULT '0',
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `holiday_compliance` varchar(5) DEFAULT 'DK',
  `time_zone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
