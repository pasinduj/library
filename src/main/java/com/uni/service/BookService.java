package com.uni.service;

import com.uni.entity.Book;
import com.uni.repo.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book updated) {
        return bookRepository.findById(id)
                .map(b -> {
                    b.setTitle(updated.getTitle());
                    b.setAuthor(updated.getAuthor());
                   // b.setisbnnumber(updated.getisbnnumber());
                    return bookRepository.save(b);
                }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
