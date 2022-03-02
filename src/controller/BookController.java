package controller;

import business.Book;
import dataaccess.DataAccessFacade;

public class BookController {
    private final DataAccessFacade dataAccessFacade;

    public BookController(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;
    }

    public void addNewBook(Book book) {
        dataAccessFacade.saveNewBook(book);
    }
}
