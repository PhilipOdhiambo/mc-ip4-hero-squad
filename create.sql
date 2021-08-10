CREATE DATABASE heroSquad;
\c heroSquad;

CREATE TABLE heroes (
      id int PRIMARY KEY auto_increment,
      name VARCHAR,
      age int,
      power VARCHAR,
      weakness VARCHAR,
      squadId int
);

CREATE TABLE squads (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    cause VARCHAR,
    maxSize int
);

CREATE DATABASE heroSquad_test WITH TEMPLATE heroSquad;