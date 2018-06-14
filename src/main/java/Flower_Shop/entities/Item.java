package Flower_Shop.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {
	@Id
	private int id;
	private String name, image;
	private int cost;
	
	public Item() {
		super();
	}

	public Item(int id, String name, String image, int cost) {
		super();
		this.name = name;
		this.image = image;
		this.cost = cost;
		this.id = id;
	}
	
	public Item(int id, String name, int cost) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", cost=" + cost + ", id=" + id + "]";
	}				
}
