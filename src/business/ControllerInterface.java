package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public String getMemberInfo(String id);
	public String getAllBookInfo();
	public boolean addMultipleBookCopy(String id, int numberOfCopy);
	public boolean addBook(String isbn, String title, int maxCheckoutDays, String[] authorIds);
	public boolean addMember(String memberId, String fname, String lname, String tel, Address add);
	public boolean addAuthor(String authorId, String fname, String lname, String tel, Address add, String bio);
	public boolean addEntry(String memberId, String isbn);
	//
	public String getMemberCheckoutEntry(String id);
	public LibraryMember getMember(String memberId);
	public Book getBook(String isbn);
	
	
	public List<LibraryMember> getAllMembers();
	public List<Book> getAllBooks();
	public List<String[]> getMemberRecords(String id);
}
