package Flower_Shop.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Basket {

	@Id
	private int id;
	private Map<String, Basket_Item> items_ = new HashMap<String, Basket_Item>();
	private int cost;
	
	public Basket(){}
	
	public Basket(int id, Map<String, Basket_Item> items_, int cost) {
		super();
		this.id = id;
		this.items_ = items_;
		this.cost = cost;
	}

	public Basket(int id) {
		super();
		this.id = id;
		this.cost = 0;
	}
	
	public void add(Item item, int count){
		Basket_Item value = items_.get(Integer.toString(item.getId()));
		if (value != null) {
			count += value.getCount();
		}
		this.items_.put(Integer.toString(item.getId()), new Basket_Item(item, count));
		this.cost += (item.getCost()*count);
	}
	
	public void remove(Item item){
		items_.remove(Integer.toString(item.getId()));
		this.cost -= item.getCost();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String,Basket_Item> getItems() {
		return items_;
	}

	public void setItems(Map<String, Basket_Item> items_) {
		this.items_ = items_;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Basket [id=" + id + ", items=" + items_ + ", cost=" + cost + "]";
	}
}
