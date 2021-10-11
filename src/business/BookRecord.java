package business;

import java.io.Serializable;

import java.util.List;

public class BookRecord implements Serializable {
	private static final long serialVersionUID = 1697448801016877416L;
	/**
	 * 
	 */

	private List<CheckoutEntry> record;
	private String isbn;
	
	public BookRecord(String isbn, List<CheckoutEntry> record) {
		this.record = record;
		this.isbn = isbn;
	}
	
	public List<CheckoutEntry> getRecord() {
		return record;
	}

	public List<CheckoutEntry> getCheckoutrecord() {
		return record;
	}
	public void addEntry(CheckoutEntry e) {
		record.add(e);
	}
	
	@Override
	public String toString() {
		for(CheckoutEntry ent: record) {
			System.out.println(ent); 
		}
		return "";
	}

	public String getISBN() {
		return isbn;
	}
}
