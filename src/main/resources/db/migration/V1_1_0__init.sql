CREATE SEQUENCE users_id_seq START 1 INCREMENT 1;

CREATE SEQUENCE documents_id_seq START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS users (
    id        INT8 PRIMARY KEY NOT NULL DEFAULT nextval('users_id_seq'),
    username  VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS documents (
    id          INT8 PRIMARY KEY NOT NULL DEFAULT nextval('documents_id_seq'),
    title       VARCHAR(255) NOT NULL UNIQUE,
    created_at  DATE NOT NULL,
    valid_at    DATE NOT NULL,
    promisor    VARCHAR(255) NOT NULL,
    promisee    VARCHAR(255) NOT NULL,
    status      VARCHAR(255) NOT NULL,
    is_deleted  BOOLEAN NOT NULL
);

ALTER SEQUENCE users_id_seq OWNED BY users.id;

ALTER SEQUENCE documents_id_seq OWNED BY documents.id;



