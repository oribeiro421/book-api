package nt.consult.livros.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(

        @Schema(name = "title", description = "Titulo do livro")
        @NotBlank(message = "Title is required")
        String title,

        @Schema(name = "author", description = "Autor do livro")
        @NotBlank(message = "Author is required")
        String author,

        @Schema(name = "publicationYear", description = "Ano de publicação do livro")
        @NotNull(message = "Publication year is required")
        Integer publicationYear
) {
}
