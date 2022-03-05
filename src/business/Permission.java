package business;

import dataaccess.Auth;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    public enum PermissionID {
        AddNewLibraryMember("2"), CheckoutBook("3"), AddCopyOfExistingBook("4"), AddABook("5"),
        PrintCheckoutRecord("6"), ShowOverdueRecord("7"), Logout("0");

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

    public Permission(PermissionID id, String name) {
        this.id = id;
        this.name = name;
    }

    public PermissionID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
