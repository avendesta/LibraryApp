package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	
	public void loadNewBookMap(List<Book> bookList);
	public void loadNewUserMap(List<User> userList);
	public void loadNewMemberMap(List<LibraryMember> memberList);
}
