package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	LibraryMember getMember(String memberId);
	Book getBook(String isbn);
	public void saveNewMember(LibraryMember member);
	void saveNewBook(Book book);
}
