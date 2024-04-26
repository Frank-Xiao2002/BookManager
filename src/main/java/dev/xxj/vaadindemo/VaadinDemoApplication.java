package dev.xxj.vaadindemo;

import dev.xxj.vaadindemo.entity.Author;
import dev.xxj.vaadindemo.entity.Book;
import dev.xxj.vaadindemo.repo.AuthorRepository;
import dev.xxj.vaadindemo.repo.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class VaadinDemoApplication {
    private final BookRepository br;
    private final AuthorRepository ar;

    public VaadinDemoApplication(BookRepository br, AuthorRepository ar) {
        this.br = br;
        this.ar = ar;
    }

    public static void main(String[] args) {
        SpringApplication.run(VaadinDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup() {
        return args -> {
            log.info("Setting up data");
            List<Author> authors = List.of(
                    new Author("John Doe", 30),
                    new Author("Jane", 25),
                    new Author("Alice", 35)
            );
            ar.saveAll(authors);

            List<Book> books = List.of(
                    new Book("Book 1", authors.get(0), 10),
                    new Book("Book 2", authors.get(1), 20),
                    new Book("Book 3", authors.get(2), 30)
            );
            br.saveAll(books);
            log.info("Data setup complete");
        };
    }
}
