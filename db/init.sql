ALTER DATABASE evaluaciondb SET timezone TO 'GMT';
SET TIMEZONE='GMT';

\c evaluaciondb postgres

CREATE TABLE users (
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(64) NOT NULL,
       email VARCHAR(128) NOT NULL,
       password VARCHAR(300) NOT NULL,
       enabled BOOLEAN NOT NULL DEFAULT true,
       UNIQUE(email)
);

CREATE TABLE roles (
       id SMALLSERIAL PRIMARY KEY,
       name VARCHAR(64) NOT NULL
);

CREATE TABLE users_roles (
       user_id BIGINT REFERENCES users(id) NOT NULL,
       role_id SMALLINT REFERENCES roles(id) NOT NULL,
       PRIMARY KEY(user_id, role_id)
);

CREATE TABLE privileges (
       id SMALLSERIAL PRIMARY KEY,
       name VARCHAR(64) NOT NULL
);

CREATE TABLE roles_privileges (
       role_id SMALLINT REFERENCES roles(id) NOT NULL,
       privilege_id SMALLINT REFERENCES privileges(id) NOT NULL,
       PRIMARY KEY(role_id, privilege_id)
);

--
GRANT SELECT, REFERENCES ON TABLE privileges TO evaluacion;
GRANT SELECT, REFERENCES ON TABLE roles TO evaluacion;
GRANT SELECT, INSERT, UPDATE, REFERENCES ON TABLE users TO evaluacion;
GRANT SELECT, INSERT, DELETE, UPDATE, REFERENCES ON TABLE users_roles TO evaluacion;
GRANT SELECT, INSERT, UPDATE, REFERENCES ON TABLE roles_privileges TO evaluacion;

GRANT ALL PRIVILEGES ON SEQUENCE privileges_id_seq TO evaluacion;
GRANT ALL PRIVILEGES ON SEQUENCE roles_id_seq TO evaluacion;
GRANT ALL PRIVILEGES ON SEQUENCE users_id_seq TO evaluacion;

-- datos iniciales
INSERT INTO roles (name) VALUES ('administrador');
INSERT INTO roles (name) VALUES ('encargado');

INSERT INTO privileges (name) VALUES ('create');
INSERT INTO privileges (name) VALUES ('read');
INSERT INTO privileges (name) VALUES ('update');
INSERT INTO privileges (name) VALUES ('delete');

-- administrador
INSERT INTO roles_privileges (role_id,privilege_id) VALUES (1,1);
INSERT INTO roles_privileges (role_id,privilege_id) VALUES (1,2);
INSERT INTO roles_privileges (role_id,privilege_id) VALUES (1,3);
INSERT INTO roles_privileges (role_id,privilege_id) VALUES (1,4);

-- encargado
INSERT INTO roles_privileges (role_id,privilege_id) VALUES (2,2);


-- Usuarios
INSERT INTO users (name, email, password) VALUES ('administrador', 'jose@rufina.link', '$2a$12$buxyLs3i3rVWk4ThLn3wqepLnHlkqZxB5P/TPYTlLzY2AVG52Ld2a');
INSERT INTO users (name, email, password) VALUES ('encargado', 'josrr@ymail.com', '$2a$12$wp.bAO9Wk2nkQPNecv7DrueKVL/pJ/jNLU26B9MBlZnq3mI5zGAtW');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
