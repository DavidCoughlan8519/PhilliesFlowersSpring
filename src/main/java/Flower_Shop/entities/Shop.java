package Flower_Shop.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop {

	@Id
	private int id;
	private String name;
	
	public Shop(){}

	public Shop(int id, String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "shop [name=" + name + "]";
	}
	
	
}
