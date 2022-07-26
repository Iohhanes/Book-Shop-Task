package com.academia.bookshop.repository;

import com.academia.bookshop.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT DISTINCT bs_books.* FROM bs_books\n" +
            "    JOIN bs_book_tag bbt ON bs_books.id = bbt.book_id\n" +
            "    JOIN bs_tags bt ON bt.id = bbt.tag_id\n" +
            "    WHERE lower(bs_books.title) LIKE CONCAT('%',:search,'%') OR lower(bt.title) LIKE CONCAT('%',:search,'%')",
            nativeQuery = true)
    List<Book> search(@Param("search") String searchValue);
}
