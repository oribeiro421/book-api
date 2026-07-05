package nt.consult.livros.service;

import nt.consult.livros.handler.exception.BookNotFoundException;
import nt.consult.livros.model.Book;
import nt.consult.livros.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldSaveBookSuccessfully() {
        Book book = createBook();

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.save(book);
        assertNotNull(result);
        assertEquals(book.getTitle(), result.getTitle());

        verify(bookRepository).save(book);
    }

    @Test
    void shouldUpdateBookSuccessfully() {
        Book existingBook = createBook();
        Book updatedBook = new Book();
        updatedBook.setTitle("Maze Runner: Prova de Fogo");
        updatedBook.setPublicationYear(2010);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book result = bookService.update(1L, updatedBook);
        assertEquals("Maze Runner: Prova de Fogo", result.getTitle());
        assertEquals("James Dasher", result.getAuthor());
        assertEquals(2010, result.getPublicationYear());

        verify(bookRepository).findById(1L);
        verify(bookRepository).save(existingBook);
    }

    @Test
    void shouldThrowBookNotFoundWhenUpdating() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Book book = new Book();
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.update(1L, book));
        assertEquals("Book id: 1 not found!", exception.getMessage());

        verify(bookRepository).findById(1L);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void shouldDeleteBookSuccessfully() {
        Book book = createBook();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        bookService.delete(1L);

        verify(bookRepository).findById(1L);
        verify(bookRepository).delete(book);
    }

    @Test
    void shouldThrowBookNotFoundWhenDeleting() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.delete(1L));
        assertEquals("Book id: 1 not found!", exception.getMessage());

        verify(bookRepository).findById(1L);
        verify(bookRepository, never()).delete(any());
    }

    private Book createBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Maze Runner");
        book.setAuthor("James Dasher");
        book.setPublicationYear(2009);
        return book;
    }
}