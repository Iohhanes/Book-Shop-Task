package com.academia.bookshop.repository;

import com.academia.bookshop.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT *\n" +
            "FROM bs_authors\n" +
            "WHERE bs_authors.first_name LIKE CONCAT('%', :search, '%')\n" +
            "   OR bs_authors.last_name LIKE CONCAT('%', :search, '%')",
            nativeQuery = true)
    List<Author> search(@Param("search") String searchValue);
}
