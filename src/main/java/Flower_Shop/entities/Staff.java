package Flower_Shop.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Staff {
	
	@Id
	private int id;
	private String firstName, lastName, position, password;
	
	public Staff(){}
	
	public Staff(int staffId, String firstName, String lastName, String position, String password) {
		super();
		this.id = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setId(int staffId) {
		this.id = staffId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Staff [staffId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position="
				+ position + "]";
	}
}