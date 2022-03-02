import controller.BookController;
import controller.SecurityController;
import dataaccess.DataAccessFacade;
import ui.UI;

public class Application {
    public static void main(String[] args) {
        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        SecurityController securityController = new SecurityController(dataAccessFacade);
        BookController bookController = new BookController(dataAccessFacade);

        new UI(securityController, bookController).start();
    }
}
