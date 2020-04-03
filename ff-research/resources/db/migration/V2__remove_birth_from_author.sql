ALTER TABLE author RENAME TO _author_old;

CREATE TABLE author
(
    id INTEGER PRIMARY KEY NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
    address VARCHAR(50)
);

INSERT INTO author (id, first_name, last_name, address)
    SELECT id, first_name, last_name, address
    FROM _author_old;

DROP TABLE _author_old;

