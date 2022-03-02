import business.Address;
import business.Author;
import business.Book;
import business.Permission;
import controller.BookController;
import controller.Controller;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final BookController bookController;
    private static final DataAccessFacade dataAccessFacade;

    static  {
        dataAccessFacade = new DataAccessFacade();
        bookController = new BookController(dataAccessFacade);
    }

    public static void main(String[] args) {
        // Welcome message to the great Library System application
        System.out.println("Welcome to the great Library System application");

        do {
            // Login
            Auth authorizationLevel;
            do {
                authorizationLevel = login();
            } while (authorizationLevel == null);

            Scanner in;
            do {
                List<Permission> permissions = Permission.permissionsWithAuth(authorizationLevel);

                String functions = "";
                for (Permission permission : permissions) {
                    functions += "\n\t" + permission.getId() + "." + permission.getName();
                }
                System.out.println("Please choose your action:" + functions);

                in = new Scanner(System.in);
                int a = in.nextInt();
                switch (a) {
                    case 2: {
                        // Add a new library member to the system.
                        addNewLibraryMember();
                        break;
                    }
                    case 3: {
                        // TODO: Use case #3
                        break;
                    }

                    case 5: {
                        addNewBook();
                        break;
                    }

                    case 0: {
                        // Logout
                        authorizationLevel = null;
                        break;
                    }
                }
            } while (authorizationLevel != null);
        } while (true);
    }

    static Auth login() {
        System.out.println("Please login");

        Scanner in = new Scanner(System.in);
        System.out.print("Enter your ID: ");
        String userId = in.nextLine();

        System.out.print("Enter your password: ");
        String password = in.nextLine();

        Controller ctr = new Controller();
        Auth authorization = ctr.login(userId, password);
        System.out.println("Authorization level: " + authorization);

        return authorization;
    }

    static void addNewLibraryMember() {
        System.out.println("Add new library member");
        System.out.println("Enter ");
    }

    static void addNewBook() {
        System.out.println("Please add a new book");

        Scanner in = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String isbn = in.nextLine();

        System.out.print("Enter book title: ");
        String title = in.nextLine();

        System.out.print("Enter max checkout length: ");
        int maxCheckoutLength = in.nextInt();

        List<Author> authors = new ArrayList<>();
        while (true) {
            System.out.print("Enter book author's first name: ");
            String firstName = in.nextLine();

            System.out.print("Enter book author's last name: ");
            String lastName = in.nextLine();

            System.out.print("Enter book author's phone: ");
            String phone = in.nextLine();

            System.out.print("Enter book author's bio: ");
            String bio = in.nextLine();

            System.out.print("Enter book author's street: ");
            String street = in.nextLine();

            System.out.print("Enter book author's city: ");
            String city = in.nextLine();

            System.out.print("Enter book author's state: ");
            String state = in.nextLine();

            System.out.print("Enter book author's zip code: ");
            String zip = in.nextLine();

            authors.add(new Author(firstName, lastName, phone, new Address(street, city, state, zip), bio));

            System.out.print("Enter 1 to add another author or 0 to start saving data... ");
            int flag = in.nextInt();
            if (flag == 0) {
                break;
            }
        }

        bookController.addNewBook(new Book(isbn, title, maxCheckoutLength, authors));
        System.out.println("Added a new book successfully!");
    }
}
