BEGIN;

CREATE TYPE country AS ENUM (
    'INDIA', 'UNITED_KINGDOM', 'VATICAN', 'ITALY', 'JAPAN'
);


CREATE TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
                                     name VARCHAR(40) UNIQUE NOT NULL,
    password_digest VARCHAR(64) NOT NULL,
    salt VARCHAR(10) NOT NULL
    );

CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL CONSTRAINT not_empty_name CHECK(length(name) > 0),
    coordinates_id INT NOT NULL REFERENCES coordinates(id) ON DELETE SET NULL,
    creation_date DATE DEFAULT NOW() NOT NULL,
    height INT NOT NULL CONSTRAINT not_empty_height CHECK(length(height) > 0),
    birthday DATE,
    passportID VARCHAR NOT NULL,
    type nationality,
    location_id INT NOT NULL REFERENCES location(id),
    creator_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS location (
id SERIAL PRIMARY KEY,
 name VARCHAR NOT NULL CONSTRAINT not_empty_name CHECK(length(name) > 0),
    x BIGINT NOT NULL,
    y INT,
    z FLOAT NOT NULL,
    creation_date DATE DEFAULT NOW() NOT NULL,
    creator_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS coordinates
(
    id SERIAL PRIMARY KEY,
    x DOUBLE NOT NULL,
    y INT,
);
END;