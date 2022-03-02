package business;

public class Author extends Person {
    private String bio;

    public Author(String firstName, String lastName, String phone, Address address, String bio) {
        super(firstName, lastName, phone, address);
        this.bio = bio;
    }
}
