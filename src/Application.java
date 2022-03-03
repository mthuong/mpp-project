
import controller.BookController;
import controller.LibraryMemberController;
import controller.SecurityController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import ui.MainUI;

public class Application {
    public static void main(String[] args) {
        DataAccess dataAccess = new DataAccessFacade();
        SecurityController securityController = new SecurityController(dataAccess);
        BookController bookController = new BookController(dataAccess);
        LibraryMemberController libraryMemberController = new LibraryMemberController(dataAccess);

        new MainUI(securityController, bookController, libraryMemberController).start();
    }
}
