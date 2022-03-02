package business;

import java.io.Serial;
import java.io.Serializable;

public class BookCopy implements Serializable {
    @Serial
    private static final long serialVersionUID = 8343371874564600853L;
    private String copyNumber;

    public BookCopy (String copyNumber) {
        this.copyNumber = copyNumber;
    }
}
