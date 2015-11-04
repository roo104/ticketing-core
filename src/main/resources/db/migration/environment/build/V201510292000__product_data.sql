# Ticket Product
INSERT INTO `product` (`id`, `name`, `type`, `price`, `product_validity_id`, `application_id`, `deleted`) VALUES (1, 'TestTicketProduct', 'Test', '100', '1', '1', '0');
INSERT INTO `product_validity` (`id`, `end_delay_minutes`) VALUES (1, '60');
INSERT INTO `product_property` (`id`, `product_id`, `name`, `value`) VALUES ('1', '1', 'name1', 'val1');
INSERT INTO `invalid_buy_time` (`id`, `product_id`, `inversed`, `time_period_id`) VALUES ('1', '1', '1', '1');
INSERT INTO `time_period` (`id`, `start_date`, `end_date`, `week_day`, `start_time`, `end_time`, `time_zone`) VALUES ('1', '2015-01-01 00:00:00', '2016-01-01 00:00:00', '1', '10:00:00', '12:00:00', 'Europe/Copenhagen');

# Voucher Product
INSERT INTO `product` (`id`, `name`, `type`, `price`, `product_validity_id`, `application_id`, `deleted`) VALUES (2, 'TestVoucherProduct', 'Test', '100', '2', '1', '0');
INSERT INTO `product_validity` (`id`, `end_delay_minutes`) VALUES (2, '120');
INSERT INTO `product_property` (`id`, `product_id`, `name`, `value`) VALUES ('2', '2', 'name2', 'val2');
INSERT INTO `invalid_buy_time` (`id`, `product_id`, `inversed`, `time_period_id`) VALUES ('2', '2', '1', '2');
INSERT INTO `time_period` (`id`, `start_date`, `end_date`, `week_day`, `start_time`, `end_time`, `time_zone`) VALUES ('2', '2015-01-01 00:00:00', '2016-01-01 00:00:00', '1', '10:00:00', '12:00:00', 'Europe/Copenhagen');
INSERT INTO `price` (`id`, `value_in_tokens`, `pricable_id`, `pricable_type`, `token_pool_id`, `price_category_type`) VALUES ('2', '2', '2', '3', '1', '5');

