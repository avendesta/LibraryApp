package business;

import java.io.Serializable;
import java.util.List;

public class Record implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4751064153458182984L;
	private List<CheckoutEntry> record;

	public List<CheckoutEntry> getCheckoutrecord() {
		return record;
	}
	public void addEntry(CheckoutEntry e) {
		record.add(e);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		int count = 0;
		for(CheckoutEntry ent: record) {
			sb.append("Entry-"+count);
		}
		return sb.toString();
	}
}
