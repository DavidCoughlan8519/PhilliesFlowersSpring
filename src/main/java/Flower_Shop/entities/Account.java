package Flower_Shop.entities;

public class Account {
	
	private int customerId = 0;
	private double outStandingBalance = 0;
	private String address = "";
	
	public Account(){}
	
	public Account(int customerId, double outStandingBalance, String address) {
		super();
		this.customerId = customerId;
		this.outStandingBalance = outStandingBalance;
		this.address = address;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getOutStandingBalance() {
		return outStandingBalance;
	}
	public void setOutStandingBalance(double outStandingBalance) {
		this.outStandingBalance = outStandingBalance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Account [customerId=" + customerId + ", outStandingBalance=" + outStandingBalance + ", address="
				+ address + "]";
	}
}
