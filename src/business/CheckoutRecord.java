package business;

import java.util.*;

public class CheckoutRecord {
    private LibraryMember libraryMember;
    private List<CheckoutEntry> entries = new ArrayList<CheckoutEntry>();

    void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public void add(BookCopy book) {
        this.entries.add(new CheckoutEntry(book));
    }

    @Override
	public String toString() {
		return "CHECKOUT RECORD \nlibraryMember\n" + libraryMember + "\n entries: " + entries;
	}

	public LibraryMember getLibraryMember() {
		return libraryMember;
	}

	public List<CheckoutEntry> getEntries() {
		return entries;
	}
}
