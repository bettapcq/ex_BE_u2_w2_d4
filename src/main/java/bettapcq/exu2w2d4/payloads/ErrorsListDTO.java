package bettapcq.exu2w2d4.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO

        (String message, LocalDateTime timestamp, List<String> errors) {
}