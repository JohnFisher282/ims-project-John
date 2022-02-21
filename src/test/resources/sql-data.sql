INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `customers`(`first_name`, `surname`) VALUES('John', 'Fisher');
INSERT INTO `customers`(`first_name`, `surname`) VALUES ('Josh', 'Sawyer');

INSERT INTO `items`(`item_name`, `item_value`) VALUES('Spanner', 5);
INSERT INTO `items`(`item_name`, `item_value`) VALUES('Golden Spanner', 500);
INSERT INTO `items`(`item_name`, `item_value`) VALUES('Hammer', 7);

INSERT INTO `orders`(`fk_id`) VALUES (1);
INSERT INTO `orders`(`fk_id`) VALUES (2);
INSERT INTO `orders`(`fk_id`) VALUES (3);

INSERT INTO `items_orders`(`fk_order_id`, `fk_item_id`) VALUES (1, 1);
INSERT INTO `items_orders`(`fk_order_id`, `fk_item_id`) VALUES (2, 2);
INSERT INTO `items_orders`(`fk_order_id`, `fk_item_id`) VALUES (3, 3);