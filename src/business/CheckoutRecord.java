package business;

import java.io.Serializable;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private List<Entry> checkoutrecord;

	public List<Entry> getCheckoutrecord() {
		return checkoutrecord;
	}
	public void addEntry(Entry e) {
		checkoutrecord.add(e);
	}
}
