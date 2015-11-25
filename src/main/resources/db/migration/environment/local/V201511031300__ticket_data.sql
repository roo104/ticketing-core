INSERT INTO `ticket` (`id`, `application_id`, `serial_code`, `buy_time`, `price`, `last_status_time`, `account_id`, `tariff`, `type`, `latest_ticket_state_id`, `ticket_state`, `transaction_state`)
VALUES ('1', '1', 'serial', '2015-11-03 14:21:06', '10', '2015-11-03 14:21:41', '1', '1', 'Adult', '1', '8', '2');
INSERT INTO `ticket_property` (`id`, `ticket_id`, `name`, `value`) VALUES ('1', '1', 'name1', 'value1');
INSERT INTO `ticket_state` (`id`, `ticket_state`, `transaction_state`, `ticket_id`) VALUES ('1', '8', '2', '1');
INSERT INTO `log_entry` (`id`, `action`, `ticket_state_id`, `type`, `ticket_id`)
VALUES ('1', '80', '1', '106', '1');