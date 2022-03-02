package business;

import java.util.*;

public class LibraryMember extends Person {
    private static final long serialVersionUID = -5194813788730605865L;

    private String memberId;
    private CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
        this.memberId = memberId;
        this.checkoutRecord = null;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                "person='" + super.toString() + '\'' +
                '}';
    }

    public void setRecord(CheckoutRecord record) {
		this.checkoutRecord = record;
	}
	
	public CheckoutRecord getRecord() {
		return checkoutRecord;
	}



}
