package ui;

import business.*;
import controller.BaseController;
import controller.BookController;
import controller.OverdueData;
import exceptions.CheckoutBookException;
import exceptions.MultipleErrorsException;

import java.util.*;
import java.util.List;

public class BookUI extends BaseUI {
    private final BookController bookController;
    BookUI(Scanner scanner, BookController bookController) {
        super(scanner);
        this.bookController = bookController;
    }

    void showAddNewBookUI() {
        System.out.println("Please add a new book");

        while (true) {
            try {
                System.out.print("Enter ISBN: ");
                String isbn = in.nextLine();

                System.out.print("Enter book title: ");
                String title = in.nextLine();

                System.out.print("Enter max checkout length: ");
                int maxCheckoutLength;

                try {
                    maxCheckoutLength = Integer.parseInt(in.nextLine());
                } catch (Exception ex) {
                    throw new IllegalArgumentException("Enter a number to Max checkout length");
                }

                System.out.print("Enter number of book copies: ");
                int numberOfCopies;

                try {
                    numberOfCopies = Integer.parseInt(in.nextLine());
                } catch (Exception ex) {
                    throw new IllegalArgumentException("Enter a number to Number of book copies");
                }

                if (numberOfCopies < 1) {
                    throw new IllegalArgumentException("Number of book copies should be greater than 0");
                }

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

                    System.out.print("Enter 1 to add another author or 0 to start verifying and saving data... ");
                    int flag = in.nextInt();in.nextLine();
                    if (flag == 0) {
                        break;
                    }
                }

                Book book = new Book(isbn, title, maxCheckoutLength, authors);
                for (int i = 0; i < numberOfCopies; i++) {
                    book.addCopy();
                }

                bookController.addNewBookHandler(book);
                System.out.println("Added a new book successfully!");break;

            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (MultipleErrorsException mex) {
                handleErrors(mex.getErrors());
            }
        }
    }
    void checkoutBook() {
        System.out.println("Checking out a book ...");
        do {
            try{
                System.out.print("Enter Library Member ID: ");
                String memberID = in.nextLine();

                System.out.print("Enter ISBN: ");
                String isbn = in.nextLine();
              
                String value = bookController.checkoutBook(memberID,isbn);
                System.out.println("\nSuccess.... ");
                System.out.println("--------------------");
                System.out.println("Checkout Record >>>");
                System.out.println(value);
                System.out.println("--------------------\n");
                break;
            }catch(CheckoutBookException e){
                handleErrors(e.getErrors());
            }
        } while (true);
    }
    void addBookCopy() {
        System.out.println("Adding a book copy ...");
        do {
            try{
                System.out.print("Enter ISBN: ");
                String isbn = in.nextLine();
              
                String value = bookController.addCopy(isbn);
                System.out.println("\nSuccess....");
                System.out.println("--------------------");
                System.out.println(value);
                System.out.println("--------------------\n");
                break;
            }catch(CheckoutBookException e){
                handleErrors(e.getErrors());
            }
        } while (true);
    }

    void showOverdueRecords() {
        System.out.println("Show overdue records");
        while (true) {
            System.out.print("Enter Book's ISBN: ");
            String isbn = in.nextLine();

            try {
                System.out.println("-".repeat(134));
                List<OverdueData> overdueRecords = bookController.getOverdueRecords(isbn);
                if (overdueRecords == null || overdueRecords.isEmpty()) {
                    System.out.println("No records");
                } else {
                    System.out.println("Book's ISBN: " + isbn);
                    System.out.println("Book's title: " + overdueRecords.get(0).getBookTitle());
                    System.out.println();
                    System.out.printf("%10s %30s %70s %20s", "BOOK COPY NUMBER", "LIBRARY MEMBER", "CHECKOUT DATE", "DUE DATE");
                    System.out.println();
                    for (OverdueData overdueData : overdueRecords) {
                        System.out.format("%10s %30s %70s %20s", overdueData.getCopyNum(), overdueData.getMemberID(), overdueData.getCheckoutDate(), overdueData.getDueDate());
                        System.out.println();
                    }
                }
                System.out.println("-".repeat(134));
                break;
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
