DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS product;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE IF NOT EXISTS product
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price       INTEGER      NOT NULL

);

CREATE TABLE IF NOT EXISTS article
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR(255) NOT NULL,
    content    VARCHAR      NOT NULL,
    created    DATE         NOT NULL,
    product_id INTEGER      NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);