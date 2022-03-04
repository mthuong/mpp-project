package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;

public interface DataAccess {
	HashMap<String,Book> getBooksMap();
	HashMap<String, User> getUserMap();
	HashMap<String, LibraryMember> getMemberMap();
	LibraryMember getMember(String memberId);
	void saveNewMember(LibraryMember member);
	Book getBook(String isbn);
	void saveNewBook(Book book);
}
