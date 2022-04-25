CREATE TABLE IF NOT EXISTS "user_role" (
    user_role_ serial NOT NULL,
    user_ integer NOT NULL,
    role_ integer NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_role_),
    CONSTRAINT user_role_fkey1 FOREIGN KEY (user_) REFERENCES public."users" (user_),
    CONSTRAINT user_role_fkey2 FOREIGN KEY (role_) REFERENCES public."roles" (role_),
    CONSTRAINT user_role_uk UNIQUE (user_, role_)
);