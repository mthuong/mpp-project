package business;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private String Isbn;
    private String title;
    private int maxCheckoutLength;

    public <T> Book(String s, String the_big_fish, int i, List<T> asList) {
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public void addCopy() {
        // TODO: Implement this method
    }
}
