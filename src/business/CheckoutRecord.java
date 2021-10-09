package business;

import java.io.Serializable;
import java.util.List;

public class CheckoutRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4751064153458182984L;
	private List<Entry> checkoutrecord;

	public List<Entry> getCheckoutrecord() {
		return checkoutrecord;
	}
	public void addEntry(Entry e) {
		checkoutrecord.add(e);
	}
}
