package nt.consult.livros.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookResponse (
        @Schema(name="Id do livro", example="1")
        Long id,

        @Schema(name="Titulo do livro", example="Maze Runner")
        String title,

        @Schema(name="Autor do livro", example="James Dasher")
        String author,

        @Schema(name="Ano de publicação", example="2009")
        Integer publicationYear
) {
}
