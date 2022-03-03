package ui;

import business.Address;
import business.Author;
import business.Book;
import controller.BookController;
import exceptions.CheckoutBookException;

import java.util.*;
import java.util.List;

public class BookUI {
    private final BookController bookController;
    private final Scanner in;

    public BookUI(Scanner in, BookController bookController) {
        this.in = in;
        this.bookController = bookController;
    }

    void addNewBook() {
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
                    throw new IllegalArgumentException("Enter a number to max checkout length");
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
                    int flag = in.nextInt();
                    in.nextLine();
                    if (flag == 0) {
                        break;
                    }
                }

                bookController.addNewBook(new Book(isbn, title, maxCheckoutLength, authors));
                System.out.println("Added a new book successfully!");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
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
                System.out.println("Success.... \n");
                System.out.println("--------------------");
                System.out.println("Checkout Record >>>");
                System.out.println(value);
                System.out.println("--------------------");
                break;
            }catch(CheckoutBookException e){
                handleAddBookException(e.getErrors());
            }
        } while (true);
    }
    void addBookCopy() {
        System.out.println("Checking out a book ...");
        do {
            try{
                System.out.print("Enter ISBN: ");
                String isbn = in.nextLine();
              
                String value = bookController.addCopy(isbn);
                System.out.println("Success.... \n");
                System.out.println("--------------------");
                System.out.println(value);
                System.out.println("--------------------");
                break;
            }catch(CheckoutBookException e){
                handleAddBookException(e.getErrors());
            }
        } while (true);
    }
    private void handleAddBookException(HashMap<String, String> errors) {
        String errorMsg = "";
        for (Map.Entry<String,String> e : errors.entrySet()) {
            if(e.getKey()=="memberId"){
                errorMsg += " Member ID not found!";
            }
            if(e.getKey()=="bookcopy"){
                errorMsg += " Book copy not available!";
            }
            if(e.getKey()=="book"){
                errorMsg += " Incorrect ISBN number for the book.";
            }
        }
        errorMsg += " Please Enter Again!";
        System.out.println(errorMsg);
       
    }
}