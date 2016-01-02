/*Table structure for table `application` */

CREATE TABLE `application` (
  `id`                   INT(11)                  NOT NULL AUTO_INCREMENT,
  `shortcode`            BIGINT(15)               NOT NULL,
  `test_pattern`         TEXT,
  `control_pattern`      TEXT,
  `verification_pattern` TEXT,
  `country_id`           INT(11)                           DEFAULT NULL,
  `ttl_min_mo`           INT(11)                           DEFAULT NULL,
  `ttl_min_mt`           INT(11)                           DEFAULT NULL,
  `verification_timeout` INT(11)                           DEFAULT NULL,
  `vat`                  VARCHAR(4)                        DEFAULT NULL,
  `description`          VARCHAR(40)                       DEFAULT NULL,
  `gw_username`          VARCHAR(40)                       DEFAULT NULL,
  `gw_password`          VARCHAR(20)                       DEFAULT NULL,
  `test_mediacode`       VARCHAR(20)                       DEFAULT NULL,
  `shortcode_prefix`     VARCHAR(20)                       DEFAULT NULL,
  `gw_validity_min`      INT(11)                           DEFAULT '0',
  `shortcode_id`         INT(11)                           DEFAULT NULL,
  `action_pattern`       TEXT,
  `reminder_enabled`     TINYINT(1)                        DEFAULT '0',
  `buy_ticket_type`      INT(4)                            DEFAULT '1',
  `application_type`     INT(11)                           DEFAULT '1',
  `time_zone`            VARCHAR(100)                      DEFAULT NULL,
  `deleted`              TINYINT(1)                        DEFAULT '0',
  `parent`               INT(1) UNSIGNED          NOT NULL DEFAULT '0',
  `serialcode_prefix`    VARCHAR(10)                       DEFAULT NULL,
  `account_source`       ENUM('ARS', 'mTicketDB') NOT NULL DEFAULT 'ARS',
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `application_property` */

CREATE TABLE `application_property` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `application_id` INT(11)          DEFAULT NULL,
  `name`           VARCHAR(100)     DEFAULT NULL,
  `value`          TEXT,
  `description`    TEXT,
  PRIMARY KEY (`id`),
  KEY `application_id_name` (`application_id`, `name`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE `account` (
  `id`                   INT(11)   NOT NULL AUTO_INCREMENT,
  `application_id`       INT(11)   NOT NULL,
  `msisdn`               BIGINT(20)         DEFAULT NULL,
  `created`              TIMESTAMP NULL     DEFAULT NULL,
  `blacklisted`          SMALLINT(1)        DEFAULT NULL,
  `amount_used`          INT(11)            DEFAULT NULL,
  `buy_attempts`         INT(11)            DEFAULT '0',
  `successful_purchases` INT(11)   NOT NULL DEFAULT '0',
  `blacklist_expiredate` TIMESTAMP NULL     DEFAULT NULL,
  `billing_account_id`   INT(11)            DEFAULT '0',
  `ars_account_id`       BIGINT(20)         DEFAULT '0',
  `ars_user_id`          BIGINT(20)         DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `msisdn_and_application_id` (`msisdn`, `application_id`),
  KEY `billing_account_id` (`billing_account_id`),
  KEY `ars_id` (`ars_account_id`, `ars_user_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE `account_identifier_type` (
  `identifier_type`       TINYINT(4) NOT NULL,
  `identifier_type_value` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`identifier_type`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;


CREATE TABLE `account_identifier` (
  `id`               INT(11)      NOT NULL AUTO_INCREMENT,
  `account_id`       INT(11)      NOT NULL,
  `identifier_value` VARCHAR(250) NOT NULL,
  `identifier_type`  INT(5)       NOT NULL,
  `application_id`   INT(11)      NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifier_value` (`identifier_value`, `identifier_type`, `application_id`),
  KEY `account_id` (`account_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;


/*Table structure for table `invalid_buy_time` */

CREATE TABLE `invalid_buy_time` (
  `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id`     BIGINT(20)          DEFAULT NULL,
  `start_date`     TIMESTAMP  NULL     DEFAULT NULL,
  `end_date`       TIMESTAMP  NULL     DEFAULT NULL,
  `week_day`       SMALLINT(1)         DEFAULT '0',
  `start_time`     TIME                DEFAULT NULL,
  `end_time`       TIME                DEFAULT NULL,
  `inversed`       TINYINT(1) NOT NULL DEFAULT '0',
  `time_period_id` BIGINT(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `price_table` */

CREATE TABLE `price_table` (
  `id`                     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name`                   VARCHAR(255)        DEFAULT NULL,
  `deleted`                TINYINT(1)          DEFAULT NULL,
  `standard_period_length` BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `product` */

CREATE TABLE `product` (
  `id`                  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name`                VARCHAR(255)        DEFAULT NULL,
  `last_sold`           TIMESTAMP  NULL     DEFAULT NULL,
  `type`                VARCHAR(255)        DEFAULT NULL,
  `price`               INT(11)             DEFAULT NULL,
  `product_validity_id` BIGINT(20)          DEFAULT NULL,
  `application_id`      INT(11)             DEFAULT NULL,
  `deleted`             SMALLINT(1)         DEFAULT NULL,
  `category_id`         INT(11)             DEFAULT NULL,
  `place_of_production` VARCHAR(255)        DEFAULT NULL,
  `gui_product_type`    INT(11)             DEFAULT '0',
  `certificate_enabled` SMALLINT(1)         DEFAULT '0',
  `activated`           TINYINT(1) NOT NULL DEFAULT '1',
  `product_variant`     INT(11)             DEFAULT '0',
  `price_table_id`      BIGINT(20)          DEFAULT NULL,
  `vat`                 VARCHAR(6)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product` (`name`, `application_id`),
  KEY `application_id` (`application_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `product_property` */

CREATE TABLE `product_property` (
  `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20)          DEFAULT NULL,
  `name`       VARCHAR(100)        DEFAULT NULL,
  `value`      TEXT,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `product_property_name` (`name`),
  KEY `Product_id_and_name` (`product_id`, `name`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `product_validity` */

CREATE TABLE `product_validity` (
  `id`                  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start_time`          TIMESTAMP  NULL     DEFAULT NULL,
  `expire_time`         TIMESTAMP  NULL     DEFAULT NULL,
  `start_delay_minutes` INT(11)    NOT NULL DEFAULT '0',
  `end_delay_minutes`   INT(11)             DEFAULT NULL,
  `start_pattern`       VARCHAR(200)        DEFAULT NULL,
  `expire_pattern`      VARCHAR(200)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `time_period` */

CREATE TABLE `time_period` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start_date`         TIMESTAMP  NULL     DEFAULT NULL,
  `end_date`           TIMESTAMP  NULL     DEFAULT NULL,
  `week_day`           SMALLINT(1)         DEFAULT '0',
  `start_time`         TIME                DEFAULT NULL,
  `end_time`           TIME                DEFAULT NULL,
  `holiday_compliance` VARCHAR(5)          DEFAULT 'DK',
  `time_zone`          VARCHAR(100)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `price` */

CREATE TABLE `price` (
  `id`                  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `price_type`          INT(11)             DEFAULT '0',
  `value`               INT(11)             DEFAULT NULL,
  `value_in_tokens`     INT(11)             DEFAULT '0',
  `extend_duration`     TINYINT(1) NOT NULL DEFAULT '0',
  `pricable_id`         BIGINT(20)          DEFAULT NULL,
  `pricable_type`       INT(11)             DEFAULT NULL,
  `token_pool_id`       INT(11)             DEFAULT '0',
  `price_category_type` INT(5)              DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `application_hierarchy` */

CREATE TABLE `application_hierarchy` (
  `parent_id` INT(10) NOT NULL,
  `child_id`  INT(10) NOT NULL,
  UNIQUE KEY `unique_parent_child` (`parent_id`, `child_id`),
  KEY `CHILD_APPLICATION` (`child_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `country` */

CREATE TABLE `country` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT,
  `currency`      VARCHAR(3)       DEFAULT NULL,
  `code`          VARCHAR(2)       DEFAULT NULL,
  `name`          VARCHAR(40)      DEFAULT NULL,
  `msisdn_length` INT(8)           DEFAULT NULL,
  `msisdn_prefix` INT(8)           DEFAULT NULL,
  `timezone`      VARCHAR(60)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `system_property` */

CREATE TABLE system_property (
  id          INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  value       TEXT         NOT NULL,
  description TEXT,
  PRIMARY KEY (id),
  UNIQUE (name)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE `order_integrator_property` (
  `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
  `integrator_id` INT(11)    NOT NULL,
  `scheme_id`     INT(11)    NOT NULL,
  `store_card`    TINYINT(1) NOT NULL DEFAULT '0',
  `order_id`      BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `order_card_property` (
  `id`                         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `card_id`                    BIGINT(20)   NOT NULL,
  `encrypted_payment_password` VARCHAR(255) NOT NULL,
  `order_id`                   BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE `order_voucher_property` (
  `id`                         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `integrator_id`              INT(11)      NOT NULL,
  `encrypted_payment_password` VARCHAR(255) NOT NULL,
  `order_id`                   BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE `order_property` (
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(100)        DEFAULT NULL,
  `value`    TEXT,
  `order_id` BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE `order` (
  `id`   BIGINT(20) NOT NULL AUTO_INCREMENT,
  `note` TEXT,
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  CHARSET = utf8;

CREATE TABLE `order_log` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `state`     INT(4)     NOT NULL,
  `timestamp` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `order_id`  BIGINT(20),
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
)
  ENGINE = INNODB
  CHARSET = utf8;

CREATE TABLE `order_item` (
  `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id`    INT(11)    NOT NULL,
  `product_count` INT(11)    NOT NULL,
  `order_id`      BIGINT(20),
  PRIMARY KEY (`id`),
  INDEX `order_id` (`order_id`)
)
  ENGINE = INNODB
  CHARSET = utf8;

/*Table structure for table `log_entry` */

CREATE TABLE `log_entry` (
  `id`                BIGINT(20) NOT NULL AUTO_INCREMENT,
  `timestamp`         TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `action`            INT(11)             DEFAULT NULL,
  `message`           TEXT,
  `ticket_state`      INT(11)             DEFAULT NULL,
  `ticket_state_id`   BIGINT(20)          DEFAULT NULL,
  `type`              INT(11)             DEFAULT NULL,
  `ticket_id`         BIGINT(20)          DEFAULT NULL,
  `user_dn`           TEXT,
  `application_id`    INT(11)             DEFAULT NULL,
  `mo_message_id`     BIGINT(20)          DEFAULT NULL,
  `mt_message_id`     BIGINT(20)          DEFAULT NULL,
  `ticket_checker_id` INT(11)             DEFAULT NULL,
  `error_code`        INT(11)             DEFAULT '0',
  `nano_time`         BIGINT(20)          DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `stat_delivery` (`ticket_id`, `action`),
  KEY `action` (`action`, `timestamp`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `ticket` */

CREATE TABLE `ticket` (
  `id`                     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id`         INT(11)             DEFAULT NULL,
  `serial_code`            VARCHAR(30)         DEFAULT NULL,
  `alpha_shortcode`        TEXT,
  `control_code`           VARCHAR(50)         DEFAULT NULL,
  `buy_time`               TIMESTAMP  NULL     DEFAULT NULL,
  `start_time`             TIMESTAMP  NULL     DEFAULT NULL,
  `expire_time`            TIMESTAMP  NULL     DEFAULT NULL,
  `price`                  INT(11)             DEFAULT NULL,
  `price_in_tokens`        INT(6)              DEFAULT '0',
  `status`                 INT(11)             DEFAULT NULL,
  `batch_flag`             TINYINT(1)          DEFAULT NULL,
  `last_status_time`       TIMESTAMP  NULL     DEFAULT NULL,
  `smsc_info_id`           INT(11)             DEFAULT NULL,
  `account_id`             INT(11)             DEFAULT NULL,
  `test`                   TINYINT(1)          DEFAULT '0',
  `tariff`                 VARCHAR(30)         DEFAULT NULL,
  `type`                   VARCHAR(30)         DEFAULT NULL,
  `pending`                TINYINT(1)          DEFAULT NULL,
  `locked`                 VARCHAR(70)         DEFAULT NULL,
  `ts`                     TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `latest_ticket_state_id` BIGINT(20)          DEFAULT NULL,
  `payment_type_id`        INT(4)              DEFAULT '1',
  `order_channel_id`       INT(11)             DEFAULT '1',
  `ticket_kinship`         INT(4)              DEFAULT '0',
  `parent_ticket_id`       BIGINT(20)          DEFAULT NULL,
  `ticket_state`           INT(11) UNSIGNED    DEFAULT NULL,
  `transaction_state`      INT(11) UNSIGNED    DEFAULT NULL,
  `ACTION`                 INT(11) UNSIGNED    DEFAULT NULL,
  `error_code`             INT(11) UNSIGNED    DEFAULT NULL,
  `previous_ticket_id`     BIGINT(20)          DEFAULT NULL,
  `next_ticket_id`         BIGINT(20)          DEFAULT NULL,
  `information_field`      VARCHAR(55)         DEFAULT NULL,
  `ticket_variant`         INT(11)             DEFAULT '0',
  `vat`                    VARCHAR(6)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serial_code_uniq` (`application_id`, `serial_code`),
  KEY `status` (`status`),
  KEY `account_id` (`account_id`),
  KEY `dump_data` (`application_id`, `buy_time`),
  KEY `smsc_info_id` (`smsc_info_id`),
  KEY `ts` (`ts`),
  KEY `locked` (`locked`),
  KEY `ticket_kinship` (`application_id`, `parent_ticket_id`, `ticket_kinship`, `account_id`),
  KEY `control_code` (`control_code`, `application_id`),
  KEY `ticket_state` (`buy_time`, `application_id`, `ticket_state`, `transaction_state`, `ACTION`, `error_code`),
  KEY `batch_flag_transaction_state_ticket_state` (`batch_flag`, `transaction_state`, `ticket_state`),
  KEY `id_and_states` (`id`, `ticket_state`, `transaction_state`),
  KEY `informationField` (`information_field`),
  KEY `parent_ticket_id` (`parent_ticket_id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `ticket_property` */

CREATE TABLE `ticket_property` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` INT(11)             DEFAULT NULL,
  `name`      VARCHAR(55)         DEFAULT NULL,
  `value`     TEXT,
  PRIMARY KEY (`id`),
  KEY `ticket_id` (`ticket_id`),
  KEY `name_ticket_id` (`ticket_id`, `name`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

/*Table structure for table `ticket_state` */

CREATE TABLE `ticket_state` (
  `id`                BIGINT(20) NOT NULL AUTO_INCREMENT,
  `ticket_state`      INT(11)    NOT NULL DEFAULT '0',
  `transaction_state` INT(11)    NOT NULL DEFAULT '0',
  `action`            INT(11)    NOT NULL DEFAULT '0',
  `error_code`        INT(11)    NOT NULL DEFAULT '0',
  `timestamp`         TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ticket_id`         INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ticket_state` (`id`, `ticket_id`),
  KEY `ticket_id_state` (`ticket_id`, `id`)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;
