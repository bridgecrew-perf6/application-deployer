CREATE TABLE IF NOT EXISTS "users" (
    user_ serial NOT NULL,
    login varchar(128) NOT NULL,
    password varchar(128) NOT NULL,
    enabled boolean NOT NULL,
    created_at timestamp NOT NULL,
    first_name varchar(32)  NOT NULL,
    last_name varchar(32)  NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_)
);