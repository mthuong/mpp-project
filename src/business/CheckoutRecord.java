package business;

import java.io.Serializable;
import java.util.*;

public class CheckoutRecord implements Serializable{
    private static final long serialVersionUID = 8343451874564600853L;
    private List<CheckoutEntry> entries = new ArrayList<CheckoutEntry>();

    public CheckoutEntry add(BookCopy book) {
        CheckoutEntry ce = new CheckoutEntry(book);
        this.entries.add(ce);
        return ce;
    }

    @Override
	public String toString() {
		return "CHECKOUT RECORD" + "\n entries: " + entries;
	}

	public List<CheckoutEntry> getEntries() {
		return entries;
	}
}