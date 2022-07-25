CREATE TABLE IF NOT EXISTS bs_authors
(
    id SERIAL PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS bs_tags
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS bs_books
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    price DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    image_url TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author_id INTEGER NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY(author_id)
            REFERENCES bs_authors(id)
);


CREATE TABLE IF NOT EXISTS bs_book_tag
(
    book_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    CONSTRAINT fk_book
        FOREIGN KEY(book_id)
            REFERENCES bs_books(id),
    CONSTRAINT fk_tag
        FOREIGN KEY(tag_id)
            REFERENCES bs_tags(id),
    CONSTRAINT book_tag_key PRIMARY KEY(book_id, tag_id)
);



