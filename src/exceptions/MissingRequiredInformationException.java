package exceptions;

import java.util.HashMap;

public class MissingRequiredInformationException extends MultipleErrorsException {
    public MissingRequiredInformationException(HashMap<String, String> errors) {
        super("Please enter required information", errors);
    }
}
