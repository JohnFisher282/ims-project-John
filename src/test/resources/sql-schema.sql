DROP TABLE IF EXISTS `items_orders`;
DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_id` INT(11) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_id`) REFERENCES `ims`.`customers`(`id`)
    );

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT,
	`item_name` VARCHAR(40) NOT NULL,
    `item_value` DOUBLE NOT NULL,
    PRIMARY KEY (`item_id`)
    );
    
CREATE TABLE IF NOT EXISTS `ims`.`items_orders` (
	`fk_order_id` INT(11) NOT NULL,
    `fk_item_id` INT(11) NOT NULL,
    FOREIGN KEY (`fk_order_id`) REFERENCES `ims`.`orders`(`order_id`),
    FOREIGN KEY (`fk_item_id`) REFERENCES `ims`.`items`(`item_id`)
    );
