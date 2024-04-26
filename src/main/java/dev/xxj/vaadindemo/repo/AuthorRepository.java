package dev.xxj.vaadindemo.repo;

import dev.xxj.vaadindemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}