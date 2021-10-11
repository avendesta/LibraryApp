package business;

import java.time.LocalDate;
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
	
	@Override
	public List<LibraryMember> getAllMembers() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> membersHashMap = da.readMemberMap();
		List<LibraryMember> allMemberList = new ArrayList<LibraryMember>(membersHashMap.values());
		return allMemberList;
	}

	@Override
	public List<Book> getAllBooks() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> membersHashMap = da.readBooksMap();
		List<Book> allBookList = new ArrayList<Book>(membersHashMap.values());
		return allBookList;
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

	@Override
	public boolean addAuthor(String authorId, String fName, String lName, String phoneNumber, Address address,String bio) {
		  Author newAuthor = new Author(authorId, fName, lName, phoneNumber, address,bio);
		  DataAccess da = new DataAccessFacade();
		  HashMap<String, Author> authorHashMap = da.readAuthorMap();
		  authorHashMap.put(authorId, newAuthor);
		  List<Author> allAuthorsList = new ArrayList<Author>(authorHashMap.values());
//		  if(allAuthorsList==null) {return false;}
		  da.loadNewAuthorMap(allAuthorsList);
		  return true;
		 }
	
	@Override
	public boolean addEntry(String memberId, String isbn) {
		// get what is needed to create a checkout entry from memberId and isbn
        DataAccess da = new DataAccessFacade();
		LocalDate checkoutDate = LocalDate.now();
		List<Book> allBooks = getAllBooks();
		Book book = null;
		for(Book b: allBooks) {
			if(b.getIsbn().equals(isbn))
				book = b;
		}
		LibraryMember member = getMember(memberId);
		if(member == null || book == null) {
			return false;
		}
//		int maxCheckoutLength = book.getMaxCheckoutLength();
//		LocalDate dueDate = checkoutDate.plusDays(maxCheckoutLength);
		BookCopy availableBookCopy = null;
		for(BookCopy bc: book.getCopies())
			if(bc.isAvailable()) {
				availableBookCopy = bc;
				break;
			}
		if(availableBookCopy == null)
			return false;
		
		// now that we got all we need to create a checkout entry
		availableBookCopy.changeAvailability();
		// ----- YOU PROBABLY NEED TO UPDATE BOOK DATABASE , BECAUSE ITS BOOKCOPY HAS CHANGED AVAILABILITY ----
		da.loadNewBookMap(allBooks);
		
		
		CheckoutEntry newEntry = new CheckoutEntry(member, availableBookCopy, checkoutDate);
		// read the hashmap from Serialized files and add the entry to it
		HashMap<String, Records> recordHashMap = da.readMemberRecordsMap();
		recordHashMap.get(memberId).getRecord().add(newEntry);
		List<Records> allRecord = new ArrayList<Records>(recordHashMap.values());
		da.loadNewMemberRecordsMap(allRecord);
        return true;
	 }

	
	@Override
	public String getMemberCheckoutEntry(String id) {
		DataAccess da = new DataAccessFacade();

//		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
//		LibraryMember libraryMember = memberHashMap.get(id);

		HashMap<String, CheckoutEntry> memberEntryHashMap = da.readMemberCheckoutEntryMap();
		System.out.println(memberEntryHashMap.get(memberEntryHashMap.keySet().toArray()[0]));
		CheckoutEntry memberEntry = memberEntryHashMap.get(id);
		System.out.println(memberEntry); // null
		if(memberEntry == null)
			return null;
		return memberEntry.toString();
	}
	@Override
	public LibraryMember getMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
		LibraryMember libraryMember = memberHashMap.get(memberId);
		return libraryMember;
	}

	@Override
	public Book getBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookHashMap = da.readBooksMap();
		Book book = bookHashMap.get(isbn);
		return book;
	}
	
	@Override
	public List<String[]> getMemberRecords(String id){
		DataAccess da = new DataAccessFacade();
		HashMap<String, Records> memberRecordsHashMap = da.readMemberRecordsMap();
		System.out.println(Arrays.toString(memberRecordsHashMap.keySet().toArray()) );
//		System.out.println(memberRecordsHashMap.get(memberRecordsHashMap.keySet().toArray()[0]));
		Records memberRecords = memberRecordsHashMap.get(id);
//		System.out.println(memberRecords); // null
		List<String[]> recordInfo = new ArrayList<String[]>();
		String[] info;
		if(memberRecords == null)
			return recordInfo;
		for(CheckoutEntry ent: memberRecords.getRecord()) {
			info = new String[5];
			info[0] = ent.getBookCopy().getBook().getIsbn();
			info[1] = ent.getBookCopy().getBook().getTitle();
			info[2] = String.valueOf(ent.getBookCopy().getCopyNum());  
			info[3] = ent.getCheckoutDate().toString();
			info[4] = ent.isOverdue()? "Overdue":"";
//			info[5] = ent.getBookCopy().isAvailable()? "isAvailable":"notAvailable";
//			System.out.println(Arrays.toString(info));
			recordInfo.add(info);
		}
		return recordInfo;
	}
	
}
