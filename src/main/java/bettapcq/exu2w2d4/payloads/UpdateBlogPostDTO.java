package bettapcq.exu2w2d4.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateBlogPostDTO(String categoria,
                                @NotBlank(message = "Il post deve avere un titolo")
                                String titolo,
                                @NotBlank(message = "Il post deve avere un contenuto")
                                String contenuto,
                                @PositiveOrZero(message = "Min di lettura non può essere un numero negativo")
                                int minLettura) {
}
