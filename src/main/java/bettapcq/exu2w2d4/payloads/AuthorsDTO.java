package bettapcq.exu2w2d4.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record AuthorsDTO(
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 40, message = "Il nome deve essere compreso tra 2 e 40 caratteri")
        String nome,
        @NotBlank(message = "Il cognome è obbligatorio")
        @Size(min = 2, max = 40, message = "Il cognome deve essere compreso tra 2 e 40 caratteri")
        String cognome,
        @Past(message = "La data di nascita deve essere al passato")
        LocalDate dataDiNascita) {
}
