package com.academia.bookshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bs_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Date createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bs_book_tag", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
