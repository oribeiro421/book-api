package nt.consult.livros.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookUpdateRequest(

        @Schema(name = "title", description = "Titulo do livro")
        String title,

        @Schema(name = "author", description = "Autor do livro")
        String author,

        @Schema(name = "publicationYear", description = "Ano de publicação do livro")
        Integer publicationYear
) {
}
