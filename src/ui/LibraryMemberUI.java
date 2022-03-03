package ui;

import business.Address;
import business.Book;
import business.CheckoutEntry;
import business.LibraryMember;
import controller.LibraryMemberController;
import exceptions.ExistingMemberIdException;
import exceptions.MissingRequiredInformationException;

import java.util.HashMap;
import java.util.Scanner;

public class LibraryMemberUI extends BaseUI {
    private final LibraryMemberController libraryMemberController;

    public LibraryMemberUI(Scanner in, LibraryMemberController libraryMemberController) {
        super(in);
        this.libraryMemberController = libraryMemberController;
    }

    void addNewLibraryMember() {
//        HashMap<String, LibraryMember> members = dataAccessFacade.readMemberMap();
//        System.out.println(members.toString());

        System.out.println("Add new library member");
        System.out.print("Enter member ID: ");
        String memberId = in.nextLine();

        System.out.print("Enter first name: ");
        String firstName = in.nextLine();

        System.out.print("Enter last name: ");
        String lastName = in.nextLine();

        // Address
        System.out.print("Enter street: ");
        String street = in.nextLine();

        System.out.print("Enter city: ");
        String city = in.nextLine();

        System.out.print("Enter state: ");
        String state = in.nextLine();

        System.out.print("Enter zip: ");
        String zip = in.nextLine();

        System.out.print("Enter telephone: ");
        String telephone = in.nextLine();

        Address address = new Address(street, city, state, zip);
        LibraryMember member = new LibraryMember(memberId, firstName, lastName, telephone, address);

        do {
            try {
                libraryMemberController.addLibraryMember(member);
                break;
            } catch (MissingRequiredInformationException | ExistingMemberIdException e) {
                handleMemberIdException(member, e.getErrors());
            }
        } while (true);

        System.out.println("Added a new library member successfully!\n" + member.toString());
    }

    private void handleMemberIdException(LibraryMember member, HashMap<String, String> errors) {
        Scanner in = new Scanner(System.in);
        errors.keySet().forEach(key -> {
            System.out.println(errors.get(key));
            if (key == "memberId") {
                System.out.print("Enter member ID: ");
                String id = in.nextLine();
                member.setMemberId(id);
            }
        });
    }

    void showPrintCheckOutRecordUI() {
        System.out.println("Print the checkout record of library member");
        while (true) {
            System.out.print("Enter member ID: ");
            String memberId = in.nextLine();

            try {
                LibraryMember libraryMember = libraryMemberController.findMemberId(memberId);
                System.out.println("-".repeat(134));
                System.out.printf("%10s %30s %70s %20s", "BOOK ISBN", "BOOK TITLE", "CHECKOUT DATE", "DUE DATE");
                System.out.println();
                System.out.println("-".repeat(134));
                if (libraryMember.getRecord() != null && libraryMember.getRecord().getEntries() != null) {
                    for (CheckoutEntry checkoutEntry : libraryMember.getRecord().getEntries()) {
                        Book book = checkoutEntry.getBookCopy().getBook();
                        System.out.format("%10s %30s %70s %20s", book.getIsbn(), book.getTitle(), checkoutEntry.getCheckoutDate(), checkoutEntry.getDueDate());
                        System.out.println();
                    }
                } else {
                    System.out.println("No records");
                }
                System.out.println("-".repeat(134));
                break;
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
