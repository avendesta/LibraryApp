package dataaccess;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.CheckoutEntry;
import business.LibraryMember;
import business.Records;
import business.SystemController;

/**
 * This class loads data into the data repository and also sets up the storage
 * units that are used in the application. The main method in this class must be
 * run once (and only once) before the rest of the application can work
 * properly. It will create three serialized objects in the dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1728138191370783948L;

	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		td.userData();
		td.authorData();
		td.memberRecordData();
		td.addEntries();

//		DataAccess da = new DataAccessFacade();
//		System.out.println(da.readBooksMap());
//		System.out.println(da.readUserMap());
//		System.out.println(da.readMemberCheckoutEntryMap());
//		System.out.println(da.readMemberRecordsMap());
//		System.out.println(da.readBookRecordsMap());
	}

	/// create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		
		allBooks.get(1).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(1).addCopy();

		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();

		allBooks.get(3).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(3).addCopy();
		
		DataAccessFacade.loadBookMap(allBooks);
	}

	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}

	// create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);

		DataAccessFacade.loadMemberMap(members);
	}

	public void authorData() {
		DataAccessFacade.loadAuthorMap(allAuthors);
	}
	List<LibraryMember> members = new ArrayList<LibraryMember>();

	@SuppressWarnings("serial")
	public void memberRecordData() {
		allBooks.get(1).getCopy(1).setAvailability(false);
		allBooks.get(2).getCopy(1).setAvailability(false);
		List<CheckoutEntry> entryList1 = new ArrayList<CheckoutEntry>() {
			{	// for members.get(0)
//				add(new CheckoutEntry(members.get(0), allBooks.get(1).getCopy(1), LocalDate.parse("2020-04-07")));
//				add(new CheckoutEntry(members.get(0), allBooks.get(2).getCopy(1), LocalDate.parse("2020-10-07")));
			}
		};

		List<CheckoutEntry> entryList2 = new ArrayList<CheckoutEntry>() {
			{	// for members.get(1)
//				add(new CheckoutEntry(members.get(1), allBooks.get(0).getCopy(2), LocalDate.parse("2020-10-07")));
//				add(new CheckoutEntry(members.get(1), allBooks.get(2).getCopy(2), LocalDate.parse("2020-10-08")));
			}
		};

		List<CheckoutEntry> entryList3 = new ArrayList<CheckoutEntry>() {
			{	// for members.get(2)
//				add(new CheckoutEntry(members.get(2), allBooks.get(0).getCopy(1), LocalDate.parse("2020-10-07")));
//				add(new CheckoutEntry(members.get(2), allBooks.get(1).getCopy(2), LocalDate.parse("2020-09-07")));
//				add(new CheckoutEntry(members.get(2), allBooks.get(2).getCopy(3), LocalDate.parse("2020-11-12")));
//				add(new CheckoutEntry(members.get(2), allBooks.get(3).getCopy(1), LocalDate.parse("2020-09-07")));
			}
		};

		List<CheckoutEntry> entryList4 = new ArrayList<CheckoutEntry>() {
			{	// for members.get(3)
//				add(new CheckoutEntry(members.get(3), allBooks.get(0).getCopy(2), LocalDate.parse("2020-10-01")));
//				add(new CheckoutEntry(members.get(3), allBooks.get(1).getCopy(1), LocalDate.parse("2020-10-01")));
//				add(new CheckoutEntry(members.get(3), allBooks.get(1).getCopy(2), LocalDate.parse("2020-10-04")));
//				add(new CheckoutEntry(members.get(3), allBooks.get(2).getCopy(2), LocalDate.parse("2020-10-09")));
			}
		};

		List<Records> records = new ArrayList<Records>() {
			{
				add(new Records("1001", entryList1));
				add(new Records("1002", entryList2));
				add(new Records("1004", entryList4));
				add(new Records("1003", entryList3));
			}
		};
		DataAccessFacade.loadMemberRecordsMap(records);
	}
	
	public void addEntries() {
		SystemController sc = new SystemController();
		sc.addEntry("1001", "23-11451", LocalDate.parse("2021-10-10"));
		sc.addEntry("1001", "23-11451", LocalDate.parse("2021-07-10"));
		sc.addEntry("1002", "28-12331", LocalDate.parse("2021-10-01"));
		sc.addEntry("1003", "23-11451", LocalDate.parse("2020-09-10"));
		sc.addEntry("1003", "32-45348", LocalDate.parse("2019-11-11"));
		sc.addEntry("1004", "32-45348", LocalDate.parse("2021-10-10"));
	}
	
//	@SuppressWarnings("serial")
//	public void bookRecordData() {
//		List<CheckoutEntry> entryList1 = new ArrayList<CheckoutEntry>() {
//			{	// for allBooks.get(0)
//				allBooks.get(0).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(1), allBooks.get(0).getCopy(2), LocalDate.parse("2021-10-07")));
//				allBooks.get(0).getCopy(1).changeAvailability();
//				add(new CheckoutEntry(members.get(2), allBooks.get(0).getCopy(1), LocalDate.parse("2021-10-07")));
//				allBooks.get(0).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(3), allBooks.get(0).getCopy(2), LocalDate.parse("2021-10-01")));
//			}
//		};
//		
//		List<CheckoutEntry> entryList2 = new ArrayList<CheckoutEntry>() {
//			{	// for allBooks.get(1)
//				allBooks.get(1).getCopy(1).changeAvailability();
//				add(new CheckoutEntry(members.get(0), allBooks.get(1).getCopy(1), LocalDate.parse("2021-04-07")));
//				allBooks.get(1).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(2), allBooks.get(1).getCopy(2), LocalDate.parse("2021-09-07")));
//				allBooks.get(1).getCopy(1).changeAvailability();
//				add(new CheckoutEntry(members.get(3), allBooks.get(1).getCopy(1), LocalDate.parse("2021-10-01")));
//				allBooks.get(1).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(3), allBooks.get(1).getCopy(2), LocalDate.parse("2021-10-04")));
//			}
//		};
//		List<CheckoutEntry> entryList3 = new ArrayList<CheckoutEntry>() {
//			{	// for allBooks.get(2)
//				allBooks.get(2).getCopy(1).changeAvailability();
//				add(new CheckoutEntry(members.get(0), allBooks.get(2).getCopy(1), LocalDate.parse("2021-10-07")));
//				allBooks.get(2).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(1), allBooks.get(2).getCopy(2), LocalDate.parse("2021-10-08")));
//				allBooks.get(2).getCopy(3).changeAvailability();
//				add(new CheckoutEntry(members.get(2), allBooks.get(2).getCopy(3), LocalDate.parse("2020-11-12")));
//				allBooks.get(2).getCopy(2).changeAvailability();
//				add(new CheckoutEntry(members.get(3), allBooks.get(2).getCopy(2), LocalDate.parse("2021-10-09")));
//			}
//		};
//		List<CheckoutEntry> entryList4 = new ArrayList<CheckoutEntry>() {
//			{	// for allBooks.get(3)
//				allBooks.get(3).getCopy(1).changeAvailability();
//				add(new CheckoutEntry(members.get(2), allBooks.get(3).getCopy(1), LocalDate.parse("2021-09-07")));
//			}
//		};
//
//		List<BookRecord> records = new ArrayList<BookRecord>() {
//			{
//				add(new BookRecord("28-12331", entryList2));
//				add(new BookRecord("23-11451", entryList1));
//				add(new BookRecord("48-56882", entryList4));
//				add(new BookRecord("32-45348", entryList3));
//			}
//		};
//		DataAccessFacade.loadBookRecordsMap(records);
//	}
//	
	
	///////////// DATA //////////////

	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("1", "Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("2", "Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("3", "Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("4", "Andrew", "Cleveland", "976-445-2232", addresses.get(2),
					"Author of childrens' books."));
			add(new Author("5", "Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
		}
	};

	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("32-45348", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
		}
	};

	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};

}
