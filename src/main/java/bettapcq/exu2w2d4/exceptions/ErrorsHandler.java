package bettapcq.exu2w2d4.exceptions;

import bettapcq.exu2w2d4.payloads.ErrorsListDTO;
import bettapcq.exu2w2d4.payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsListDTO handleValidationException(ValidationException ex) {

        return new ErrorsListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsMsg());
    }

    @ExceptionHandler(BadReqException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadReq(BadReqException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericServerError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("C'è stato un errore, ripova più tardi", LocalDateTime.now());

    }
}
