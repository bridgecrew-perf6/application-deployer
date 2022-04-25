CREATE TYPE role_enum AS ENUM('DEFAULT_ADMIN', 'ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS "roles" (
    role_ serial NOT NULL,
    role role_enum NULL DEFAULT 'USER',
    CONSTRAINT role_pkey PRIMARY KEY (role_)
);