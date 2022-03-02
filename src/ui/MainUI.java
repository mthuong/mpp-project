package ui;

import business.Permission;
import controller.BookController;
import controller.LibraryMemberController;
import controller.SecurityController;
import dataaccess.Auth;

import java.util.List;
import java.util.Scanner;

public class MainUI {
    private final BookController bookController;
    private final SecurityController securityController;
    private final LibraryMemberController libraryMemberController;

    public MainUI(SecurityController securityController, BookController bookController, LibraryMemberController libraryMemberController) {
        this.securityController = securityController;
        this.bookController = bookController;
        this.libraryMemberController = libraryMemberController;
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        BookUI bookUI = new BookUI(in, bookController);
        LoginUI loginUI = new LoginUI(in, securityController);
        LibraryMemberUI libraryMemberUI = new LibraryMemberUI(in, libraryMemberController);

        // Welcome message to the great Library System application
        System.out.println("Welcome to the great Library System application");

        do {
            // Login
            Auth authorizationLevel;
            do {
                authorizationLevel = loginUI.login();
            } while (authorizationLevel == null);

            do {
                List<Permission> permissions = Permission.permissionsWithAuth(authorizationLevel);

                String functions = "";
                for (Permission permission : permissions) {
                    functions += "\n\t" + permission.getId().getValue() + ". " + permission.getName();
                }
                System.out.println("Please choose your action:" + functions);
                int a = in.nextInt();
                in.nextLine();
                switch (a) {
                    case 2: {
                        // Add a new library member to the system.
                        libraryMemberUI.addNewLibraryMember();
                        break;
                    }
                    case 3: {
                        // checkout book
                        bookUI.checkoutBook();
                        break;
                    }

                    case 4: {
                        // add copy to existing book
                        bookUI.addBookCopy();
                        break;
                    }

                    case 5: {
                        bookUI.addNewBook();
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
}
