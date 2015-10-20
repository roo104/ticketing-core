/*Table structure for table `application` */

CREATE TABLE `application` (
  `id` int(11) NOT NULL auto_increment,
  `shortcode` bigint(15) NOT NULL,
  `test_pattern` text,
  `control_pattern` text,
  `verification_pattern` text,
  `country_id` int(11) default NULL,
  `ttl_min_mo` int(11) default NULL,
  `ttl_min_mt` int(11) default NULL,
  `verification_timeout` int(11) default NULL,
  `vat` varchar(4) default NULL,
  `description` varchar(40) default NULL,
  `gw_username` varchar(40) default NULL,
  `gw_password` varchar(20) default NULL,
  `test_mediacode` varchar(20) default NULL,
  `shortcode_prefix` varchar(20) default NULL,
  `gw_validity_min` int(11) default '0',
  `shortcode_id` int(11) default NULL,
  `action_pattern` text,
  `reminder_enabled` tinyint(1) default '0',
  `buy_ticket_type` int(4) default '1',
  `application_type` int(11) default '1',
  `time_zone` varchar(100) default NULL,
  `deleted` tinyint(1) default '0',
  `parent` int(1) unsigned NOT NULL default '0',
  `serialcode_prefix` varchar(10) default NULL,
  `account_source` enum('ARS','mTicketDB') NOT NULL default 'ARS',
  PRIMARY KEY  (`id`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8;