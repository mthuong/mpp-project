import business.Permission;
import controller.Controller;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Application {
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
                System.out.println("Please choose function:" + functions);

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
        Scanner in = new Scanner((System.in));
        System.out.println("Add new library member");
        System.out.println("Enter member ID: ");
        String memberId = in.nextLine();

        System.out.println("Enter first name: ");
        String firstName = in.nextLine();

        System.out.println("Enter last name: ");
        String lastName = in.nextLine();

        System.out.println("Enter street: ");
        String street = in.nextLine();

        System.out.println("Enter city: ");
        String city = in.nextLine();

        System.out.println("Enter state: ");
        String state = in.nextLine();

        System.out.println("Enter zip: ");
        String zip = in.nextLine();

        System.out.println("Enter telephone: ");
        String telephone = in.nextLine();


    }
}
