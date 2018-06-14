package Flower_Shop.entities;

public class Stock {
	
	private int itemId;
	private int quantity;
	
	public Stock(){}
	
	public Stock(int itemId, int quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Stock [itemId=" + itemId + ", quantity=" + quantity + "]";
	}
}
