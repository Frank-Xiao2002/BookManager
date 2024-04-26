package dev.xxj.vaadindemo.repo;

import dev.xxj.vaadindemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByTitleContainsOrAuthor_NameContainsAllIgnoreCase(String title, String name);
}