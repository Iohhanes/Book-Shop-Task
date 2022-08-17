CREATE SEQUENCE bs_author_id_sequence
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 1000000
    START 1001;

CREATE TABLE IF NOT EXISTS bs_authors
(
    id         INT NOT NULL DEFAULT nextval('bs_author_id_sequence') PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

CREATE SEQUENCE bs_tag_id_sequence
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 10000
    START 101;

CREATE TABLE IF NOT EXISTS bs_tags
(
    id    SMALLINT     NOT NULL DEFAULT nextval('bs_tag_id_sequence') PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE SEQUENCE bs_book_id_sequence
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 1000000
    START 1001;

CREATE TABLE IF NOT EXISTS bs_books
(
    id         INT                      NOT NULL DEFAULT nextval('bs_book_id_sequence') PRIMARY KEY,
    title      VARCHAR(255),
    price      DECIMAL(15, 2)           NOT NULL DEFAULT 0.00,
    image_url  TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author_id  INTEGER                  NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES bs_authors (id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS bs_book_tag
(
    book_id INT      NOT NULL,
    tag_id  SMALLINT NOT NULL,
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES bs_books (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_tag
        FOREIGN KEY (tag_id)
            REFERENCES bs_tags (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT book_tag_key PRIMARY KEY (book_id, tag_id)
);


COPY bs_authors FROM '/tmp/data/authors.csv' DELIMITER ',' HEADER CSV;

COPY bs_tags FROM '/tmp/data/tags.csv' DELIMITER ',' HEADER CSV;

COPY bs_books (id, title, price, image_url, author_id) FROM '/tmp/data/books.csv' DELIMITER ',' HEADER CSV;

COPY bs_book_tag FROM '/tmp/data/book-tags.csv' DELIMITER ',' HEADER CSV;

