package business;

import java.io.Serializable;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
//	private CheckoutRecord memberCheckoutRecord;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
//		memberCheckoutRecord = new CheckoutRecord();
	}
	
	
	public String getMemberId() {
		return memberId;
	}
	public void addEntry(CheckoutEntry e) {
//		memberCheckoutRecord.addEntry(e);
	}
	
	@Override
	public String toString() {
		return "(Member Info: " + " ID: " + memberId + " Name: " + getFirstName() + " " + getLastName() + 
				" Phone: " + getTelephone() + " Address: " + getAddress()+")";
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
