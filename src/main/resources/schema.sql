/*
 * Copyright (c) 2018 Lisandro Fernandez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/*
 * Copyright (c) 2018 Lisandro Fernandez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


CREATE TABLE product (
    product_id IDENTITY PRIMARY KEY,
    sku VARCHAR(256) UNIQUE NOT NULL,
    name VARCHAR(256) not null,
    price DECIMAL(20, 2) not null
);

CREATE TABLE category (
    category_id IDENTITY PRIMARY KEY,
    name VARCHAR(256) UNIQUE NOT NULL,
    description varchar(256) NOT NULL
);

create table product_category(
        product_id INT8 not null,
        category_id INT8 not null,
        primary key (product_id, category_id),
        foreign key (product_id) references product(product_id) on delete cascade,
        foreign key (category_id) references category(category_id) on delete cascade
);

CREATE TABLE customer (
                          customer_id IDENTITY PRIMARY KEY,
                          name VARCHAR(256),
                          birthdate DATE
);

CREATE TABLE address (
                         id INT8 PRIMARY KEY,
                         street VARCHAR(128),
                         city VARCHAR(128),
                         country VARCHAR(128),
                         postal_code VARCHAR(16),
                         FOREIGN KEY(id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE email_address (
                               id IDENTITY PRIMARY KEY,
                               customer_id INT8 not null,
                               address VARCHAR(128),
                               FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE purchase_order (
                                order_id IDENTITY PRIMARY KEY,
                                customer_id INT8,
                                order_date DATE,
                                FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE item (
                      id IDENTITY PRIMARY KEY,
                      order_id INT8,
                      product_id INT8,
                      quantity INT,
                      total DECIMAL(20, 2),
                      FOREIGN KEY(order_id) REFERENCES purchase_order(order_id),
                      FOREIGN KEY(product_id) REFERENCES product(product_id)
);
