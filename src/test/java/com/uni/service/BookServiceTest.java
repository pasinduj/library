package com.uni.service;

import com.uni.entity.Book;
import com.uni.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring Boot in Action");
        book.setAuthor("Craig Walls");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Spring Boot in Action", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book( "Java", "Author A","2456BLM787");
        Book book2 = new Book( "Spring", "Author B","4466NVM787");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        assertEquals("Java", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBook_Success() {
        Book existing = new Book( "Old Title", "Old Author","2345NJK865");
        Book updated = new Book( "New Title", "New Author","4445NJK875");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(existing)).thenReturn(updated);

        Book result = bookService.updateBook(1L, updated);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existing);
    }

    @Test
    void testUpdateBook_NotFound() {
        Book updated = new Book( "New Title", "New Author","3456BN145");

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookService.updateBook(1L, updated));

        assertEquals("Book not found", exception.getMessage());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

}
