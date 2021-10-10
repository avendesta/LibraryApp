package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public String getMemberInfo(String id);
	public String getAllBookInfo();
	public boolean addMultipleBookCopy(String id, int numberOfCopy);
	public boolean addBook(String isbn, String title, int maxCheckoutDays, String[] authorIds);
	public boolean addMember(String memberId, String fname, String lname, String tel, Address add);
	//
	public String getMemberCheckoutEntry(String id);
	public LibraryMember getMember(String memberId);
	
	public List<LibraryMember> getAllMembers();
	public List<String[]> getMemberRecords(String id);
}
