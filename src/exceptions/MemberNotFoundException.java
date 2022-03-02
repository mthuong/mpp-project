package exceptions;
import java.util.HashMap;

public class MemberNotFoundException extends MultipleErrorsException{
    public MemberNotFoundException(HashMap<String, String> errors) {
        super("Member not found.", errors);
    }
}
