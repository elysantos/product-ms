DROP TABLE IF EXISTS products;


CREATE TABLE products
(
    id            VARCHAR(255) PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   TEXT NOT NULL,
    price         NUMERIC(15,2) NOT NULL
);