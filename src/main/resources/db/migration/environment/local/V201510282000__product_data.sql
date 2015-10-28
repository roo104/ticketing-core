INSERT INTO `product` (`id`, `name`, `type`, `price`, `product_validity_id`, `application_id`, `deleted`) VALUES (1, 'TestProduct', 'Test', '100', '1', '1', '0');
INSERT INTO `product_validity` (`id`, `end_delay_minutes`) VALUES (1, '60');
INSERT INTO `product_property` (`id`, `product_id`, `name`, `value`) VALUES ('1', '1', 'name1', 'val1');
INSERT INTO `invalid_buy_time` (`id`, `product_id`, `inversed`, `time_period_id`) VALUES ('1', '1', '1', '1');
INSERT INTO `time_period` (`id`, `start_date`, `end_date`, `week_day`, `start_time`, `end_time`, `time_zone`) VALUES ('1', '2015-01-01 00:00:00', '2016-01-01 00:00:00', '1', '10:00:00', '12:00:00', 'Europe/Copenhagen');

