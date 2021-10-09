package business;

import java.util.List;

public class CheckoutRecord {
	private List<Entry> checkoutrecord;

	public List<Entry> getCheckoutrecord() {
		return checkoutrecord;
	}
	public void addEntry(Entry e) {
		checkoutrecord.add(e);
	}
}
