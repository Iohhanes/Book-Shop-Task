INSERT INTO bs_authors(first_name, last_name)
VALUES ('Test1', 'Test1'),
       ('Test2', 'Test2');

INSERT INTO bs_tags(title)
VALUES ('Action'),
       ('Adventure'),
       ('Comic');

INSERT INTO bs_books(title, price, author_id)
VALUES ('Test book1', 34.56, 1),
       ('Test book2', 56.89, 2);

INSERT INTO bs_book_tag(book_id, tag_id)
VALUES (1,1),
       (1,2),
       (2,2),
       (2,3);






