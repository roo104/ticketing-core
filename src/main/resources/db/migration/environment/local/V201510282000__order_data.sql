INSERT INTO `order` (`id`, `note`) VALUES ('1', 'note');
INSERT INTO `order_item` (`id`, `product_id`, `product_count`, `order_id`) VALUES ('1', '1', '1', '1');
INSERT INTO `order_property` (`id`, `name`, `value`, `order_id`) VALUES ('1', 'prop1', 'val1', '1');
INSERT INTO `order_state` (`id`, `order_id`, `state`) VALUES ('1', '1', '0');
INSERT INTO `order_integrator_property` (`id`, `integrator_id`, `scheme_id`, `store_card`, `order_id`) VALUES ('1', '1', '1', '1', '1');