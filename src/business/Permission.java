package business;

import dataaccess.Auth;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private String id;
    private String name;

    private Permission(String id, String name) {
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
                permissions.add(new Permission("3", "Checkout a book for a library member"));
                permissions.add(new Permission("6", "Print the checkout record of library member"));
                break;
            }

            case ADMIN: {
                permissions.add(new Permission("2", "Add a new library member to the system."));
                permissions.add(new Permission("4", "Add a copy of an existing book to the library collection."));
                permissions.add(new Permission("5", "Add a book to the library collection."));
                break;
            }

            case BOTH: {
                permissions.add(new Permission("2", "Add a new library member to the system."));
                permissions.add(new Permission("3", "Checkout a book for a library member"));
                permissions.add(new Permission("4", "Add a copy of an existing book to the library collection."));
                permissions.add(new Permission("5", "Add a book to the library collection."));
                permissions.add(new Permission("6", "Print the checkout record of library member"));
                break;
            }
        }

        // Default permission
        permissions.add(new Permission("0", "Logout"));

        return permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
