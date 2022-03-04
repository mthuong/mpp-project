package ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BaseUI {
    protected final Scanner in;

    BaseUI (Scanner scanner) {
        this.in = scanner;
    }

    protected void handleErrors(Map<String, String> errorMap) {
        System.out.println();
        System.out.println("Errors");
        System.out.println("--------");
        List<String> errors = errorMap.values().stream().toList();
        for (int i = 0; i < errors.size(); i++) {
            System.out.println((i+1) + ") " + errors.get(i));
        }
        System.out.println("--------");
        System.out.println("Please try again!");
        System.out.println();
    }
}
