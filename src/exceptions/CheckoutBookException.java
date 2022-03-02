package exceptions;
import java.util.HashMap;

public class CheckoutBookException extends MultipleErrorsException{
    public CheckoutBookException(HashMap<String, String> errors) {
        super("Checkout book exception", errors);
    }
}
