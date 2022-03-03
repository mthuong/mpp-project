package controller;

import java.util.*;

import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import exceptions.CheckoutBookException;

public class BookController {
    private final DataAccessFacade dataAccessFacade;


    public BookController(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;

    }

    public String checkoutBook(String memberID,String ISBN) throws CheckoutBookException{
        HashMap<String, String> errors = new HashMap<>();
		Book book = this.getBook(ISBN);
        
        if (!checkExistingMemberId(memberID)) {
            errors.put("memberId", "Member id not found");
        }

        if(book==null){
            errors.put("book","Inncorrect book ISBN");
        }

        if(errors.size()>0){
            throw new CheckoutBookException(errors);
        }

        LibraryMember member = dataAccessFacade.getMember(memberID);

        BookCopy copy = null;

        Boolean bookExist = false;
	
        List<BookCopy> bc = book.getCopies();
        
        if(bc.size()>0){
            for(int i=0;i<bc.size();i++){
                if(bc.get(i).checkAvailable()){
                    bc.get(i).setAvailable(false);
                    copy = bc.get(i);
                }
            }
            
        }
        
       
        if(copy == null){
            errors.put("bookcopy","Book copy not available");
            throw new CheckoutBookException(errors);
        }
        CheckoutRecord record = (member.getRecord() == null) ? new CheckoutRecord() : member.getRecord();
		CheckoutEntry cr = record.add(copy);
		member.setRecord(record);
        dataAccessFacade.saveNewMember(member);
        dataAccessFacade.saveNewBook(copy.getBook());
        return cr.toString();
    }
    
    protected Boolean checkExistingMemberId(String memberId) {
        
        LibraryMember member = dataAccessFacade.getMember(memberId);
       
        if (member == null){
            System.out.println("here");
            return false;
        }
       return true;
    }

    public void addNewBook(Book book) {
        validate(book);
        dataAccessFacade.saveNewBook(book);
    }

    public String addCopy(String ISBN) throws CheckoutBookException{
        Book book = this.getBook(ISBN);
        HashMap<String, String> errors = new HashMap<>();
        if(book==null){
            errors.put("book","Inncorrect book ISBN");
            throw new CheckoutBookException(errors);
        }
        return book.addCopy();
    }

    public Book getBook(String isbn) {
        Collection<Book> books = dataAccessFacade.readBooksMap().values();
        Book book = null;
        for (Book b : books) {
            if (b.getIsbn() != null && b.getIsbn().equals(isbn.trim())) {
                book = b;
            }
        }
        return book;
    }

    public void validate(Book book) {
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new InputMismatchException("ISBN is required");
        }

        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
    }
}
