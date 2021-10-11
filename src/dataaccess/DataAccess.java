package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.BookRecord;
import business.CheckoutEntry;
import business.LibraryMember;
import business.Records;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	
	public HashMap<String, Author> readAuthorMap();
	public HashMap<String, CheckoutEntry> readMemberCheckoutEntryMap();
	public HashMap<String, Records> readMemberRecordsMap();
	public HashMap<String, Records> readBookRecordsMap();
	
	public void saveNewMember(LibraryMember member); 
	
	public void loadNewBookMap(List<Book> bookList);
	public void loadNewUserMap(List<User> userList);
	public void loadNewAuthorMap(List<Author> authorrList) ;
	public void loadNewMemberMap(List<LibraryMember> memberList);
	public void loadNewMemberCheckoutEntryMap(List<CheckoutEntry> entryList);
	public void loadNewMemberRecordsMap(List<Records> recordsList);
	public void loadNewBookRecordsMap(List<BookRecord> recordsList);
}
