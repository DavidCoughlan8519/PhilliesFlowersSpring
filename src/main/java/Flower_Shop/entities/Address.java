package Flower_Shop.entities;

public class Address {

	private String address, town, city, firstname, lastname, postcode;

	public Address() {
		super();
	}

	public Address(String firstname, String lastname, String address, String town, String city, String postcode) {
		super();
		this.address = address;
		this.town = town;
		this.city = city;
		this.firstname = firstname;
		this.lastname = lastname;
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", town=" + town + ", city=" + city + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", postcode=" + postcode + "]";
	}
	
	
}
