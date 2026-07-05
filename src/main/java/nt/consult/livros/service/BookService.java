package nt.consult.livros.service;

import lombok.RequiredArgsConstructor;
import nt.consult.livros.handler.exception.BookNotFoundException;
import nt.consult.livros.model.Book;
import nt.consult.livros.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update (Long id, Book book){
        Book bookUpdated = findById(id);
        return bookRepository.save(updateBook(bookUpdated, book));
    }

    public void delete(Long id){
        Book book = findById(id);
        bookRepository.delete(book);
    }

    private Book findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book id: " + id + " not found!"));
    }

    private Book updateBook(Book bookUpdated, Book newBook){
        bookUpdated.setTitle(newBook.getTitle() != null ? newBook.getTitle() : bookUpdated.getTitle());
        bookUpdated.setAuthor(newBook.getAuthor() != null ? newBook.getAuthor() : bookUpdated.getAuthor());
        bookUpdated.setPublicationYear(newBook.getPublicationYear() != null ? newBook.getPublicationYear() : bookUpdated.getPublicationYear());
        return bookUpdated;
    }
}
