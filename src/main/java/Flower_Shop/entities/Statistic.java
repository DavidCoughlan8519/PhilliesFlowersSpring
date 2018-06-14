package Flower_Shop.entities;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Statistic {

	private double userBalance = 0;
	private double shopRevenue = 0;
	private double outStandingAmount = 0;
	private Map<String,Order> orders = new HashMap<String,Order>();
	
	Statistic(){}


	public Statistic(double userBalance, double shopRevenue, double outStandingAmount, Map<String, Order> orders) {
		super();
		this.userBalance = userBalance;
		this.shopRevenue = shopRevenue;
		this.outStandingAmount = outStandingAmount;
		this.orders = orders;
	}
	

	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	public double getShopRevenue() {
		return shopRevenue;
	}

	public void setShopRevenue(double shopRevenue) {
		this.shopRevenue = shopRevenue;
	}

	public double getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(double outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public Map<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order){
		if(order != null){
			orders.put(Integer.toString((int) order.getId()),order);
			this.shopRevenue += order.getCost();
			balance(order.getCost());
		}
	}
	
	public void removeOrder(Order order){
		if(order != null){
			this.orders.remove(order.getId());
		}
	}
	
	private void balance(double income){
		if(outStandingAmount > 0){
			outStandingAmount = outStandingAmount - income;
			income = 0;
			if(outStandingAmount < 0){
				income = -outStandingAmount;
				outStandingAmount = 0;
			}	
		}
		userBalance += income;
	}

	@Override
	public String toString() {
		return "Statistic [userBalance=" + userBalance + ", shopRevenue=" + shopRevenue + ", outStandingAmount="
				+ outStandingAmount + ", orders=" + orders + "]";
	}
}
