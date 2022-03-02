package business;

import java.io.Serializable;

public class BookCopy implements Serializable {
    private static final long serialVersionUID = 8343371874564600853L;
    private String copyNumber;
    private Boolean isAvailable;
    private Book book;

    public BookCopy (String copyNumber,Book book,Boolean availabe) {
        this.copyNumber = copyNumber;
        this.book=book;
        this.isAvailable = availabe;
    }
    public void setAvailable(Boolean value){
        this.isAvailable = value;
    }
    public Boolean checkAvailable(){
       return this.isAvailable;
    }
    public String getCopyNumber(){
        return this.copyNumber;
    }
    public Book getBook(){
        return this.book;
    }
}
