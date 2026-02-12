package bettapcq.exu2w2d4.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Il record con id " + id + " non esiste");
    }
}
