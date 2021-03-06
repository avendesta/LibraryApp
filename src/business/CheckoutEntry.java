package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1252707950055877724L;
	private BookCopy bookCopy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private LibraryMember libraryMember;
	
	public CheckoutEntry(LibraryMember libraryMember, BookCopy bookCopy, LocalDate checkoutDate) {
		this.libraryMember = libraryMember;
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.bookCopy.setAvailability(false);
		dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength()); 
	}
	public CheckoutEntry(LibraryMember libraryMember, BookCopy bookCopy) {
		this(libraryMember, bookCopy, LocalDate.now());
	}


	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public boolean isOverdue() {
		LocalDate today = LocalDate.now();
		return dueDate.isBefore(today);
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public LibraryMember getLibraryMember() {
		return libraryMember;
	}
	@Override
	public String toString() {
		return String.format("BookTitle: %s, CopyNumber: %s, CheckoutDate: %s, DueDate: %s",  
					bookCopy.getBook().getTitle(), bookCopy.getCopyNum(), checkoutDate, dueDate);
	}
}
