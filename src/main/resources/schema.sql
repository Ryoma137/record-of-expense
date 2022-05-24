CREATE TABLE IF NOT EXISTS amount(
    id long PRIMARY KEY,
    name VARCHAR(128),
    price int,
    category VARCHAR(128),
    comments VARCHAR(256)
);