INSERT INTO users (username, password, role)
VALUES ('admin', '$2y$10$K630qDNrU8Iq9IMOFo0pKuq3.vzMzwJlZwkEPstgUq.nGBRWklYwa', 'ROLE_ADMIN');
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$b2gy/cOyyzSaTytLYcrMAOOLFIGg1djHXK9brCRmm/kakgp83hi4i', 'ROLE_USER');

INSERT INTO documents (title, created_at, valid_at, promisor, promisee, status, is_deleted)
VALUES ('Договор аренды квартиры', '2022-05-14', '2022-10-14', 'Иванов', 'Петров', 'CREATED', false);
INSERT INTO documents (title, created_at, valid_at, promisor, promisee, status, is_deleted)
VALUES ('Договор поставки продуктов', '2022-08-10', '2022-09-10', 'Алексеев', 'Андреев', 'SIGNED', false);
INSERT INTO documents (title, created_at, valid_at, promisor, promisee, status, is_deleted)
VALUES ('Договор купли-продажи дома', '2022-03-25', '2022-04-25', 'Иванов', 'Андреев', 'APPROVED', false);
INSERT INTO documents (title, created_at, valid_at, promisor, promisee, status, is_deleted)
VALUES ('Договор подряда', '2022-07-09', '2022-10-09', 'Петров', 'Алексеев', 'ARCHIVED', false);
INSERT INTO documents (title, created_at, valid_at, promisor, promisee, status, is_deleted)
VALUES ('Договор аренды дома', '2022-05-17', '2022-10-17', 'Авдеенко', 'Васильев', 'APPROVED', false);
