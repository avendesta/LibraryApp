package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(id.length()*password.length()==0)
			throw new LoginException("Input fields can not be empty!!");
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	@Override
	public String getMemberInfo(String id) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
		LibraryMember libraryMember = memberHashMap.get(id);
		if(libraryMember == null)
			return null;
		return libraryMember.toString();
	}
	
	@Override
	public String getAllBookInfo() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksHashMap = da.readBooksMap();
		List<Book> allBookList = new ArrayList<Book>(booksHashMap.values());
		if(allBookList == null)
			return null;
		return Arrays.toString( allBookList.toArray());
	}
	
	// addMultipleBookCopy needs to save the book copy to database
	@Override
	public boolean addMultipleBookCopy(String id, int numberOfCopy) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksHashMap = da.readBooksMap();
		Book book = booksHashMap.get(id);
		if(book == null)
			return false;
		book.addMultipleCopy(numberOfCopy);
		List<Book> allBookList = new ArrayList<Book>(booksHashMap.values());
		da.loadNewBookMap(allBookList);
		return true;
	}
	
	@Override
	public boolean addBook(String isbn, String title, int maxCheckoutDays, String[] authorIds) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> authorsHashMap = da.readAuthorMap();
		List<Author> bookAuthors = new ArrayList<Author>();
		for(String id: authorIds) {
			if(id != null) {
				bookAuthors.add(authorsHashMap.get(id));
			}
		}
		Book newBook = new Book(isbn, title, maxCheckoutDays, bookAuthors);
		HashMap<String, Book> booksHashMap = da.readBooksMap();
		booksHashMap.put(isbn, newBook);
		List<Book> allBookList = new ArrayList<Book>(booksHashMap.values());
		da.loadNewBookMap(allBookList);
		return true;
	}
	@Override
	public boolean addMember(String memberId, String fname, String lname, String tel,Address add) {
		LibraryMember newMember = new LibraryMember(memberId, fname, lname, tel, add);
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> membersHashMap = da.readMemberMap();
		membersHashMap.put(memberId, newMember);
		List<LibraryMember> allMembersList = new ArrayList<LibraryMember>(membersHashMap.values());
		da.loadNewMemberMap(allMembersList);
		return true;
	}
	
}
