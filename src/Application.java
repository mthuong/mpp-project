
import controller.BookController;
import controller.LibraryMemberController;
import controller.SecurityController;
import dataaccess.DataAccessFacade;
import exceptions.MemberNotFoundException;
import ui.UI;

public class Application {
    public static void main(String[] args) throws MemberNotFoundException {
        DataAccessFacade dataAccessFacade = new DataAccessFacade();
       // SecurityController securityController = new SecurityController(dataAccessFacade);
        BookController bookController = new BookController(dataAccessFacade);
       // LibraryMemberController libraryMemberController = new LibraryMemberController(dataAccessFacade);
       bookController.checkoutBook("1001","23-11451");
        //new UI(securityController, bookController, libraryMemberController).start();
    }
}
