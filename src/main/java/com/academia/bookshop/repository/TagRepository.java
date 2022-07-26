package com.academia.bookshop.repository;

import com.academia.bookshop.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT *\n" +
            "FROM bs_tags\n" +
            "WHERE bs_tags.title LIKE CONCAT('%', :search, '%')",
            nativeQuery = true)
    List<Tag> search(@Param("search") String searchValue);
}
