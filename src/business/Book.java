package business;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 6110690276685962829L;
    private String isbn;
    private String title;
    private int maxCheckoutLength;
    private List<Author> authors;
    private List<BookCopy> bookCopies = new ArrayList<>();

    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public void addCopy() {
        bookCopies.add(new BookCopy(UUID.randomUUID().toString()));
    }
}
