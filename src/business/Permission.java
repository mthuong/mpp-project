package business;

import dataaccess.Auth;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    public enum PermissionID {
        AddNewLibraryMember("2"), CheckoutBook("3"), AddCopyOfExistingBook("4"), AddABook("5"),
        PrintCheckoutRecord("6"), Logout("0");

        private String value;

        PermissionID(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private final PermissionID id;
    private final String name;

    private Permission(PermissionID id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Factory method to get permissions base on Authorizationn
     * @param authorization
     * @return List of permissions for authorization
     */
    public static List<Permission> permissionsWithAuth(Auth authorization) {
        List<Permission> permissions = new ArrayList<>();
        switch (authorization) {
            case LIBRARIAN: {
                permissions.add(new Permission(PermissionID.CheckoutBook, "Checkout a book for a library member"));
                permissions.add(new Permission(PermissionID.PrintCheckoutRecord, "Print the checkout record of library member"));
                break;
            }

            case ADMIN: {
                permissions.add(new Permission(PermissionID.AddNewLibraryMember, "Add a new library member to the system."));
                permissions.add(new Permission(PermissionID.AddCopyOfExistingBook, "Add a copy of an existing book to the library collection."));
                permissions.add(new Permission(PermissionID.AddABook, "Add a book to the library collection."));
                break;
            }

            case BOTH: {
                permissions.add(new Permission(PermissionID.AddNewLibraryMember, "Add a new library member to the system."));
                permissions.add(new Permission(PermissionID.CheckoutBook, "Checkout a book for a library member"));
                permissions.add(new Permission(PermissionID.AddCopyOfExistingBook, "Add a copy of an existing book to the library collection."));
                permissions.add(new Permission(PermissionID.AddABook, "Add a book to the library collection."));
                permissions.add(new Permission(PermissionID.PrintCheckoutRecord, "Print the checkout record of library member"));
                break;
            }
        }

        // Default permission
        permissions.add(new Permission(PermissionID.Logout, "Logout"));

        return permissions;
    }

    public PermissionID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
