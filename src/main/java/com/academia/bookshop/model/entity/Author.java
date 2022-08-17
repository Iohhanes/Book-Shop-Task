package com.academia.bookshop.model.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bs_authors")
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@BatchSize(size = 1000)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorIdGenerator")
    @SequenceGenerator(name = "authorIdGenerator", sequenceName = "bs_author_id_sequence", allocationSize = 1)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
