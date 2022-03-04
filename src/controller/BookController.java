package controller;

import java.util.*;

import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccess;
import exceptions.CheckoutBookException;

public class BookController extends BaseController {
    public BookController(DataAccess dataAccess) {
        super(dataAccess);
    }

    public String checkoutBook(String memberID,String ISBN) throws CheckoutBookException{
        HashMap<String, String> errors = new HashMap<>();
        
        if (!checkExistingMemberId(memberID)) {
            errors.put("memberId", "Member id not found");
        }

        if(!checkBookExist(ISBN)){
            errors.put("book","Inncorrect book ISBN");
        }

        if(errors.size()>0){
            throw new CheckoutBookException(errors);
        }

        Book book = dataAccess.getBook(ISBN);
        LibraryMember member = dataAccess.getMember(memberID);

        BookCopy copy = null;
	
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
        dataAccess.saveNewMember(member);
        dataAccess.saveNewBook(copy.getBook());
        return cr.toString();
    }

    protected Boolean checkBookExist(String isbn){
        Book book = dataAccess.getBook(isbn);
       
        if (book == null){
            return false;
        }
       return true;
    }
    
    protected Boolean checkExistingMemberId(String memberId) {
        
        LibraryMember member = dataAccess.getMember(memberId);
       
        if (member == null){
            return false;
        }
       return true;
    }

    public void addNewBookHandler(Book book) {
        validate(book);
        dataAccess.saveNewBook(book);
    }

    public String addCopy(String ISBN) throws CheckoutBookException{
        Book book = dataAccess.getBook(ISBN);
        HashMap<String, String> errors = new HashMap<>();
        if(book==null){
            errors.put("book","Inncorrect book ISBN");
            throw new CheckoutBookException(errors);
        }
        
        String result =  book.addCopy();
        dataAccess.saveNewBook(book);
        return "Book: "+book.getTitle()+"\n"+"Copies: "+book.getCopies().size()+"\n"+"Added Copy: "+result;
    }

    public Book getBook(String isbn) {
        Collection<Book> books = dataAccess.getBooksMap().values();
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
