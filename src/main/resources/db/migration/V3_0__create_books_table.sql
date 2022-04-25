CREATE TABLE IF NOT EXISTS "books" (
    book_ serial NOT NULL,
    title         varchar(32)  NULL,
    CONSTRAINT book_pkey PRIMARY KEY (book_)
);