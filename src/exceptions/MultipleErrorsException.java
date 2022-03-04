package exceptions;

import java.util.HashMap;
import java.util.Map;

public class MultipleErrorsException extends RuntimeException {
    private final Map<String, String> errors;

    public MultipleErrorsException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
