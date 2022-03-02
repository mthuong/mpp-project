package ui;

import controller.BookController;
import controller.SecurityController;
import dataaccess.Auth;

import java.util.Scanner;

public class LoginUI {
    private final SecurityController securityController;
    private final Scanner in;

    public LoginUI(Scanner in, SecurityController securityController) {
        this.in = in;
        this.securityController = securityController;
    }

    Auth login() {
        System.out.println("Please login");

        System.out.print("Enter your ID: ");
        String userId = in.nextLine();

        System.out.print("Enter your password: ");
        String password = in.nextLine();

        Auth authorization = securityController.login(userId, password);
        System.out.println("Authorization level: " + authorization);

        return authorization;
    }
}
