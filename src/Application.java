import controller.Controller;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.HashMap;
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
                System.out.println("Please choose your action:" +
                        "\n\t2. Add a new library member to the system." +
                        "\n\t3. Checkout a book (if available) for a library member." +
                        "\n\t0. Logout");

                in = new Scanner(System.in);
                int a = in.nextInt();
                switch (a) {
                    case 2: {
                        // TODO: Use case #2
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
}
