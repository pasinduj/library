package com.uni.service;

import com.uni.entity.Book;
import com.uni.repo.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// Log4j2 import
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        logger.info("Adding a new book: {}", book.getTitle());
        Book saved = bookRepository.save(book);
        logger.debug("Book saved with ID: {}", saved.getId());
        return saved;
    }

    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        logger.debug("Number of books found: {}", books.size());
        return books;
    }

    public Book updateBook(Long id, Book updated) {
        logger.info("Updating book with ID: {}", id);
        return bookRepository.findById(id)
                .map(b -> {
                    logger.debug("Original book details: {}", b);
                    b.setTitle(updated.getTitle());
                    b.setAuthor(updated.getAuthor());
                   // b.setisbnnumber(updated.getisbnnumber());
                    logger.info("Book with ID {} updated successfully", id);
                    return bookRepository.save(b);
                }).orElseThrow(
                        () -> {
                            logger.error("Book with ID {} not found", id);
                            return new RuntimeException("Book not found");
                        }

                );
    }

    public void deleteBook(Long id) {
        logger.info("Deleting book with ID: {}", id);
        bookRepository.deleteById(id);
        logger.info("Book with ID {} deleted successfully", id);
    }

}
