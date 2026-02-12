package bettapcq.exu2w2d4.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMsg;

    public ValidationException(List<String> errorsMsg) {

        super("Il payload ha degli errori");
        this.errorsMsg = errorsMsg;
    }
}