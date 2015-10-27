CREATE TABLE `account` (
  `id` int(11) NOT NULL auto_increment,
  `application_id` int(11) NOT NULL,
  `msisdn` bigint(20) default NULL,
  `created` timestamp NULL default NULL,
  `blacklisted` smallint(1) default NULL,
  `amount_used` int(11) default NULL,
  `buy_attempts` int(11) default '0',
  `successful_purchases` int(11) NOT NULL default '0',
  `blacklist_expiredate` timestamp NULL default NULL,
  `billing_account_id` int(11) default '0',
  `ars_account_id` bigint(20) default '0',
  `ars_user_id` bigint(20) default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `msisdn_and_application_id` (`msisdn`,`application_id`),
  KEY `billing_account_id` (`billing_account_id`),
  KEY `ars_id` (`ars_account_id`,`ars_user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `account_identifier_type` (
  `identifier_type` tinyint(4) NOT NULL,
  `identifier_type_value` varchar(32) default NULL,
  PRIMARY KEY  (`identifier_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `account_identifier` (
  `id` int(11) NOT NULL auto_increment,
  `account_id` int(11) NOT NULL,
  `identifier_value` varchar(250) NOT NULL,
  `identifier_type` int(5) NOT NULL,
  `application_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `identifier_value` (`identifier_value`,`identifier_type`,`application_id`),
  KEY `account_id` (`account_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;