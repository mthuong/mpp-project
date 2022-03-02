package controller;

import java.util.*;

import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import exceptions.MemberNotFoundException;

public class BookController {
    private final DataAccessFacade dataAccessFacade;


    public BookController(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;

    }

    public void checkoutBook(String memberID,String ISBN) throws MemberNotFoundException{
        HashMap<String, String> errors = new HashMap<>();
		Collection<Book> books = dataAccessFacade.readBooksMap().values();
        
        errors = checkExistingMemberId(memberID);

        if (errors != null && errors.size() > 0) {
            System.out.println("here");
            throw new MemberNotFoundException(errors);
        }

        LibraryMember member = dataAccessFacade.getMember(memberID);

        BookCopy copy = null;

		for(Book b : books) {
			if(b.getIsbn()!=null && b.getIsbn().equals(ISBN.trim())) {
				List<BookCopy> bc = b.getCopies();
               
                if(bc.size()>0){
                    for(int i=0;i<bc.size();i++){
                       if(bc.get(i).checkAvailable()){
                            bc.get(i).setAvailable(false);
                            copy = bc.get(i);
                        }
                    }
                    
                }
			}
		}
        CheckoutRecord record = (member.getRecord() == null) ? new CheckoutRecord() : member.getRecord();
		record.add(copy);
		member.setRecord(record);
    }

    protected HashMap<String, String> checkExistingMemberId(String memberId) {
        
        LibraryMember member = dataAccessFacade.getMember(memberId);
       
        if (member == null){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("memberId", "Member id " + memberId + " is existing.");
            return errors;
        }
       return null;
    }

    public void addNewBook(Book book) {
        dataAccessFacade.saveNewBook(book);
    }

    public String addCopy(Book book){
        return book.addCopy();
    }
}
