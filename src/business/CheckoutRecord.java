package business;

import java.io.Serializable;
import java.util.*;

public class CheckoutRecord implements Serializable{
    private static final long serialVersionUID = 8343451874564600853L;
    private LibraryMember libraryMember;
    private List<CheckoutEntry> entries = new ArrayList<CheckoutEntry>();

    void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public CheckoutEntry add(BookCopy book) {
        CheckoutEntry ce = new CheckoutEntry(book);
        this.entries.add(ce);
        return ce;
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
