CREATE TABLE `application_property` (
  `id` int(11) NOT NULL auto_increment,
  `application_id` int(11) default NULL,
  `name` varchar(100) default NULL,
  `value` text,
  `description` text,
  PRIMARY KEY  (`id`),
  KEY `application_id_name` (`application_id`,`name`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
