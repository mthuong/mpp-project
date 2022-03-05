package controller;

import java.time.LocalDate;
import java.util.*;

import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccess;
import exceptions.CheckoutBookException;
import exceptions.MultipleErrorsException;

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

    private Boolean checkBookExist(String isbn){
        Book book = dataAccess.getBook(isbn);
       
        if (book == null){
            return false;
        }
       return true;
    }
    
    private Boolean checkExistingMemberId(String memberId) {
        
        LibraryMember member = dataAccess.getMember(memberId);
       
        if (member == null){
            return false;
        }
       return true;
    }

    public void addNewBookHandler(Book book) {
        Map<String, String> errors = validate(book);
        if (!errors.isEmpty()) {
            throw new MultipleErrorsException("Add new book exceptions", errors);
        }

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

    private Map<String, String> validate(Book book) {
        Map<String, String> errors = new HashMap<>();

        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            errors.put("ISBN", "ISBN is required");
        }

        if (dataAccess.getBook(book.getIsbn()) != null) {
            errors.put("ISBN", "ISBN is existed");
        }

        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            errors.put("Title", "Title is required");
        }

        if (book.getMaxCheckoutLength() < 1) {
            errors.put("Max checkout length", "Max checkout length should be greater than 1");
        }

        return errors;
    }

    public List<OverdueData> getOverdueRecords(String isbn) throws RuntimeException {

        List<OverdueData> result = new ArrayList<>();

        Book book = dataAccess.getBook(isbn);
        if (null == book) {
            throw new RuntimeException(String.format("Book ISBN [%s] is not existed.", isbn));
        }
        LocalDate today = LocalDate.now();
        Map<String, LibraryMember> memberMap = dataAccess.getMemberMap();
        for (LibraryMember member: memberMap.values()) {
            if (member.getRecord() == null) continue;
            List<CheckoutEntry> entries = member.getRecord().getEntries();
            for (CheckoutEntry entry: entries) {
                BookCopy bookCopy = entry.getBookCopy();
                if (entry.getDueDate().isBefore(today) && !bookCopy.checkAvailable()
                        && bookCopy.getBook().getIsbn().equals(isbn)) {
                    String bookTitle = book.getTitle();
                    String copyNumber = bookCopy.getCopyNumber();
                    String memberID = member.getMemberId();
                    LocalDate checkoutDate = entry.getCheckoutDate();
                    LocalDate dueDate = entry.getDueDate();

                    OverdueData data = new OverdueData(bookTitle, copyNumber, memberID, checkoutDate, dueDate);
                    result.add(data);
                }
            }
        }

        return result;
    }
}
