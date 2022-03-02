package exceptions;

import java.util.HashMap;

public class MultipleErrorsException extends Exception {
    private final HashMap<String, String> errors;

    protected MultipleErrorsException(String message, HashMap<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }
}
