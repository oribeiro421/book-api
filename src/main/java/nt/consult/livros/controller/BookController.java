package nt.consult.livros.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nt.consult.livros.dtos.BookRequest;
import nt.consult.livros.dtos.BookResponse;
import nt.consult.livros.dtos.BookUpdateRequest;
import nt.consult.livros.mapper.BookMapper;
import nt.consult.livros.model.Book;
import nt.consult.livros.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Tag(name = "Livro", description = "CRUD de livros")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @Operation(description = "Busca todos os livros")
    public ResponseEntity<List<BookResponse>> getAll(){
        return new ResponseEntity<>(bookService.getAll().stream().map(bookMapper::toResponse).toList(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Realiza um cadastro de um novo livro")
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest bookRequest){
        Book book = bookMapper.toEntity(bookRequest);
        return new ResponseEntity<>(bookMapper.toResponse(bookService.save(book)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Atualiza os dados de um livro existente")
    public ResponseEntity<BookResponse> update(@PathVariable("id") Long id, @RequestBody BookUpdateRequest bookRequest){
        Book book = bookMapper.toUpdateEntity(bookRequest);
        return new ResponseEntity<>(bookMapper.toResponse(bookService.update(id, book)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Exclui um livro")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
