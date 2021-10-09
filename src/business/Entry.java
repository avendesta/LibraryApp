package business;

import java.time.LocalDate;

public class Entry {
	private BookCopy bookCopy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	
	public Entry(BookCopy bookCopy, LocalDate checkoutDate) {
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.bookCopy.changeAvailability();
		dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength()); 
	}
	public Entry(BookCopy bookCopy) {
		this(bookCopy, LocalDate.now());
	}

	public boolean isOverdue() {
		LocalDate today = LocalDate.now();
		return dueDate.isAfter(today);
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
}
