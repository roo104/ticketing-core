CREATE TABLE system_property(
id int NOT NULL auto_increment,
name varchar(100) not null,
value text not null,
description text,
PRIMARY KEY(id),
UNIQUE(name)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

