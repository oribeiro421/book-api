package nt.consult.livros.mapper;

import nt.consult.livros.dtos.BookRequest;
import nt.consult.livros.dtos.BookResponse;
import nt.consult.livros.dtos.BookUpdateRequest;
import nt.consult.livros.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequest request);
    Book toUpdateEntity(BookUpdateRequest request);
    BookResponse toResponse(Book book);
}
