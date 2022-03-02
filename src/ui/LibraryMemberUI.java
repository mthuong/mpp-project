package ui;

import business.Address;
import business.LibraryMember;
import controller.LibraryMemberController;
import controller.SecurityController;
import exceptions.ExistingMemberIdException;
import exceptions.MissingRequiredInformationException;

import java.util.HashMap;
import java.util.Scanner;

public class LibraryMemberUI {
    private final LibraryMemberController libraryMemberController;
    private final Scanner in;

    public LibraryMemberUI(Scanner in, LibraryMemberController libraryMemberController) {
        this.in = in;
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
}
