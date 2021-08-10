CREATE DATABASE herosquad;
\c herosquad;

CREATE TABLE heroes (
      id SERIAL PRIMARY KEY,
      name VARCHAR,
      age int,
      power VARCHAR,
      weakness VARCHAR,
      squadId int
);

CREATE TABLE squads (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    cause VARCHAR,
    maxSize int
);

CREATE DATABASE herosquad_test WITH TEMPLATE herosquad;