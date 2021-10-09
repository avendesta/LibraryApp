package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord memberCheckoutRecord;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		memberCheckoutRecord = new CheckoutRecord();
	}
	
	
	public String getMemberId() {
		return memberId;
	}
	public void addEntry(Entry e) {
		memberCheckoutRecord.addEntry(e);
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "\n\tID: " + memberId + "\n\tName: " + getFirstName() + " " + getLastName() + 
				"\n\tPhone: " + getTelephone() + "\r\n\tAddress: " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
