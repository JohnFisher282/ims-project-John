INSERT INTO `ims`.`customers`(`first_name`, `surname`)
VALUES('John', 'Fisher'),
('Josh', 'Sawyer'),
('Gavin', 'Long'),
('Billy-Bob', 'Townsend'),
('Jean', 'Grey'),
('Harry', 'McSwain'),
('Omar', 'Abu-Tair'),
('Farzan', 'Chowdhury'),
('Adil', 'Akbarali'),
('Joe', 'Price');

INSERT INTO `ims`.`orders`(`fk_id`)
VALUES (1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10);

INSERT INTO `ims`.`items`(`item_name`, `item_value`)
VALUES('Spanner', 5),
('Golden Spanner', 500),
('Hammer', 7),
('Golden Hammer', 700),
('Saab 9-3', 2200),
('Yellow Saab 9-3', 12200),
('Pineapple', 2),
('Golden Pineapple', 2000),
('Postcard', 1),
('Postcard of a Saab 9-3', 15);

INSERT INTO `ims`.`items_orders`(`fk_order_id`, `fk_item_id`)
VALUES (1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);
