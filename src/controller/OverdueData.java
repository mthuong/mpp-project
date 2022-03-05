package controller;

import java.time.LocalDate;

public class OverdueData {
	
	private String bookTitle;
	
	private String copyNum;
	
	private String memberID;
	
	private LocalDate checkoutDate;
	
	private LocalDate dueDate;
	
	public OverdueData(String bookTitle, String copyNum, String memberID,
			LocalDate checkoutDate, LocalDate dueDate) {
		
		this.bookTitle = bookTitle;
		this.copyNum = copyNum;
		this.memberID = memberID;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getCopyNum() {
		return copyNum;
	}

	public String getMemberID() {
		return memberID;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
}
