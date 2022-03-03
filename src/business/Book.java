package business;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.TrustManagerFactory;

public class Book implements Serializable {
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

    public Integer getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public String addCopy() {
        String uid = UUID.randomUUID().toString();
        bookCopies.add(new BookCopy(uid,this,true));
        return uid;
    }
    public List<BookCopy> getCopies(){
        return bookCopies;
    }
    
}
