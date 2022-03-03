package ui;

import controller.BookController;
import controller.SecurityController;
import dataaccess.Auth;

import java.util.Scanner;

public class LoginUI extends BaseUI {
    private final SecurityController securityController;

    public LoginUI(Scanner in, SecurityController securityController) {
        super(in);
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
