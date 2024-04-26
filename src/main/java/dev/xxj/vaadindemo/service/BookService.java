package dev.xxj.vaadindemo.service;

import dev.xxj.vaadindemo.entity.Book;
import dev.xxj.vaadindemo.repo.AuthorRepository;
import dev.xxj.vaadindemo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public List<Book> findAll(String filterTextValue) {
        if (filterTextValue == null || filterTextValue.isBlank())
            return bookRepository.findAll();
        return bookRepository.findByTitleContainsOrAuthor_NameContainsAllIgnoreCase(filterTextValue, filterTextValue);
    }

    public void saveBook(Book book) {
        authorRepository.save(book.getAuthor());
        bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public long countBooks() {
        return bookRepository.count();
    }
}
