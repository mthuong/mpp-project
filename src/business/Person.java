package business;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6898663745395794106L;
    private String fistName;
    private String lastName;
    private String phone;
    private Address address;

    public Person(String fistName, String lastName, String phone, Address address) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
}
