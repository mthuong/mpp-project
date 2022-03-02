package exceptions;

import java.util.HashMap;

public class ExistingMemberIdException extends MultipleErrorsException {
    public ExistingMemberIdException(HashMap<String, String> errors) {
        super("Member Id is already existing.", errors);
    }
}
