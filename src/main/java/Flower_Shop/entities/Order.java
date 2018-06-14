package Flower_Shop.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private long id;
	private LocalDateTime date_time = LocalDateTime.now();
	private double cost = 0;
	private Map<String, Basket_Item> items_ = new HashMap<String, Basket_Item>();
	private Address address;
	
	public Order() {
		super();
	}
	
	public Order(long id, double cost, Map<String, Basket_Item> items_, Address address) {
		super();
		this.id = id;
		this.cost = cost;
		this.items_ = items_;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDate_time() {
		return date_time;
	}

	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Map<String, Basket_Item> getItems_() {
		return items_;
	}

	public void setItems_(Map<String, Basket_Item> items_) {
		this.items_ = items_;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date_time=" + date_time + ", cost=" + cost + ", items_=" + items_ + "]";
	}
	
	
	
	
	
	
	
}
