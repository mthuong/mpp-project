package dataaccess;

import business.Permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Auth implements Serializable {
	LIBRARIAN, ADMIN, BOTH;

	public List<Permission> getPermissions() {
		List<Permission> permissions = new ArrayList<>();
		switch (this) {
			case LIBRARIAN: {
				permissions.add(new Permission(Permission.PermissionID.CheckoutBook, "Checkout a book for a library member"));
				permissions.add(new Permission(Permission.PermissionID.PrintCheckoutRecord, "Print the checkout record of library member"));
				break;
			}

			case ADMIN: {
				permissions.add(new Permission(Permission.PermissionID.AddNewLibraryMember, "Add a new library member to the system."));
				permissions.add(new Permission(Permission.PermissionID.AddCopyOfExistingBook, "Add a copy of an existing book to the library collection."));
				permissions.add(new Permission(Permission.PermissionID.AddABook, "Add a book to the library collection."));
				break;
			}

			case BOTH: {
				permissions.add(new Permission(Permission.PermissionID.AddNewLibraryMember, "Add a new library member to the system."));
				permissions.add(new Permission(Permission.PermissionID.CheckoutBook, "Checkout a book for a library member"));
				permissions.add(new Permission(Permission.PermissionID.AddCopyOfExistingBook, "Add a copy of an existing book to the library collection."));
				permissions.add(new Permission(Permission.PermissionID.AddABook, "Add a book to the library collection."));
				permissions.add(new Permission(Permission.PermissionID.PrintCheckoutRecord, "Print the checkout record of library member"));
				break;
			}
		}

		// Default permission
		permissions.add(new Permission(Permission.PermissionID.Logout, "Logout"));

		return permissions;
	}
}
