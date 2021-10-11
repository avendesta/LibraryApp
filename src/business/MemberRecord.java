package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MemberRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4751064153458182984L;
	private List<CheckoutEntry> record;
	private String libraryMemberId;
	
	public MemberRecord(String libraryMemberId, List<CheckoutEntry> record) {
		this.record = record;
		this.libraryMemberId = libraryMemberId;
	}
	
	public List<CheckoutEntry> getRecord() {
		return record;
	}

	public List<CheckoutEntry> getCheckoutRecord() {
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

	public String getLibraryMemberId() {
		return libraryMemberId;
	}
}
