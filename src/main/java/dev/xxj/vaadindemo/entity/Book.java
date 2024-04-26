package dev.xxj.vaadindemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
//    @PropertyId("author")
    private Author author;

    @Column(name = "amount", nullable = false)
    @PositiveOrZero
    private Integer amount;

    public Book(String title, Author author, Integer amount) {
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    public Book() {
        author = new Author();
    }
}