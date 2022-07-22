INSERT INTO bs_authors(id, first_name, last_name)
VALUES (1, 'Test1', 'Test1'),
       (2, 'Test2', 'Test2');

INSERT INTO bs_tags(id, title)
VALUES (1, 'Action'),
       (2, 'Adventure'),
       (3, 'Comic');

INSERT INTO bs_books(id, title, price, author_id)
VALUES (1, 'Test book1', 34.56, 1),
       (2, 'Test book2', 56.89, 2);

INSERT INTO bs_book_tag(book_id, tag_id)
VALUES (1,1),
       (1,2),
       (2,2),
       (2,3);






