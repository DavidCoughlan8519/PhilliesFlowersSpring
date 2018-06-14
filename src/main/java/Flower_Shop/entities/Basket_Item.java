package Flower_Shop.entities;

public class Basket_Item {

	private Item item;
	private int count;
	
	public Basket_Item(Item item, int count) {
		super();
		this.item = item;
		this.count = count;
	}

	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Basket_Item [item=" + item + ", count=" + count + "]";
	}
	
	
	
}
