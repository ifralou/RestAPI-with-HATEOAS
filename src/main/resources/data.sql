
INSERT INTO product (sku, name, price) VALUES ('keyboard', 'Keyboard', 7.99);
INSERT INTO product (sku, name, price) VALUES ('tv', 'Television', 351.96);
INSERT INTO product (sku, name, price) VALUES ('shirt', 'Shirt', 3.57);
INSERT INTO product (sku, name, price) VALUES ('bed', 'Bed', 131.00);
INSERT INTO product (sku, name, price) VALUES ('cell-phone', 'Cell Phone', 1000.00);
INSERT INTO product (sku, name, price) VALUES ('spoon', 'Spoon', 1.00);
INSERT INTO product (sku, name, price) VALUES ('fork', 'Fork', 1.00);
INSERT INTO product (sku, name, price) VALUES ('table spoon', 'Table spoon', 1.00);
INSERT INTO product (sku, name, price) VALUES ('eatitall', 'Eat it all', 4.00);

insert into category(name, description) values ('Computer Accessories', 'We hope you love products we recommend! All of them are safe & sound!');
insert into category(name, description) values ('Furniture', 'Very comfy and soft!');
insert into category(name, description) values ('Personal Electronics', 'For work and leisure!');
insert into category(name, description) values('Sale', 'Items under the sales!');
insert into category(name, description) values('Home Inventory', 'Items under the sales!');

insert into product_category(product_id, category_id) values (1, 1);
insert into product_category(product_id, category_id) values (5, 3);
insert into product_category(product_id, category_id) values (2, 4);
insert into product_category(product_id, category_id) values (6, 5);
insert into product_category(product_id, category_id) values (7, 5);
insert into product_category(product_id, category_id) values (8, 5);
insert into product_category(product_id, category_id) values (9, 5);
insert into product_category(product_id, category_id) values (2, 5);

INSERT INTO customer (name, birthdate) VALUES ('John Doe', '1960-10-30');
INSERT INTO customer (name, birthdate) VALUES ('Pepito Pérez', '1954-07-15');
INSERT INTO customer (name, birthdate) VALUES ('Cosme Fulanito', '1956-05-12');

INSERT INTO address (id, street, city, country, postal_code) VALUES (1, 'La Habana 4310', 'Villa Devoto', 'Argentina', '1000');
INSERT INTO address (id, street, city, country, postal_code) VALUES (2, '10 rue Henri Poincaré', 'Saint-Fargeau', 'France', '13014');
INSERT INTO address (id, street, city, country, postal_code) VALUES (3, 'Calle Falsa 123', 'Aldea en Cabo', 'Spain', '01102');

INSERT INTO email_address (id, customer_id, address) VALUES (1, 1, 'john.doe@gmail.com');
INSERT INTO email_address (id, customer_id, address) VALUES (2, 1, 'john.doe@hotmail.com');
INSERT INTO email_address (id, customer_id, address) VALUES (3, 2, 'pepito@perez.com');
INSERT INTO email_address (id, customer_id, address) VALUES (4, 3, 'cosme@fulanito.com');

INSERT INTO purchase_order (order_id, customer_id, order_date) VALUES (1, 2, '2018-01-04');
INSERT INTO purchase_order (order_id, customer_id, order_date) VALUES (2, 1, '2018-02-13');
INSERT INTO purchase_order (order_id, customer_id, order_date) VALUES (3, 2, '2018-02-25');

INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (1, 1, 1, 10, 79.90);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (2, 1, 2, 2, 703.92);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (3, 1, 3, 7, 24.99);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (4, 2, 4, 2, 262.00);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (5, 2, 5, 15, 15000.00);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (6, 3, 1, 7, 55.93);
INSERT INTO item (id, order_id, product_id, quantity, total) VALUES (7, 3, 6, 18, 18.00);