FROM postgres:14

COPY /src/main/resources/data/authors.csv /tmp/data/

COPY /src/main/resources/data/tags.csv /tmp/data/

COPY /src/main/resources/data/books.csv /tmp/data/

COPY /src/main/resources/data/book-tags.csv /tmp/data/

