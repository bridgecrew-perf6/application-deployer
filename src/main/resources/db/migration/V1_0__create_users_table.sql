CREATE TABLE IF NOT EXISTS "users" (
    user_ serial NOT NULL,
    email              varchar(128) NULL,
    first_name         varchar(32)  NULL,
    last_name          varchar(32)  NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_)
);