package business;
import java.time.LocalDate;

import business.*;

public class CheckoutEntry {
	private BookCopy bookcopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    CheckoutEntry(BookCopy bookcopy){
        this.bookcopy = bookcopy;
        this.checkoutDate = LocalDate.now();
        this.dueDate = this.checkoutDate.plusDays(this.bookcopy.getBook().getMaxCheckoutLength());
    }

    @Override
	public String toString() {
		return "CheckoutEntry:  \nbook: " + bookcopy.getBook().getTitle() + "\ncheckoutDate: " + checkoutDate + "\ndueDate: " + dueDate + "\n\n";
	}

	public BookCopy getBookCopy() {
		return bookcopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
}
