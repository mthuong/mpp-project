package business;

import java.io.Serial;

public class LibraryMember extends Person {
    @Serial
    private static final long serialVersionUID = -5194813788730605865L;

    private String memberId;

    public LibraryMember(String memberId, String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
        this.memberId = memberId;
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


}
