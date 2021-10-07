package business;

public class Item {
	private String itemName;
	private boolean highlight = false;
	public Item(String item, boolean visible) {
	   itemName = item;
	   highlight = visible;
    }
	@Override
	public boolean equals(Object ob) {
		if(ob.getClass() != Item.class) return false;
		Item item = (Item)ob;
		return itemName.equals(item.itemName);
	}
	public String getItemName() {
		return itemName;
	}
	public boolean highlight() {
		return highlight;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
}
