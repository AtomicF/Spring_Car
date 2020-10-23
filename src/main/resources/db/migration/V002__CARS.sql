CREATE TABLE cars
(
    id         SERIAL primary key,
    model       VARCHAR(20),
    color      VARCHAR(20),
    number_of_doors INTEGER default 4
);

INSERT into cars (model, color, number_of_doors)
VALUES ('Toyota', 'Black', 4);