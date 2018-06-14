package Flower_Shop.entities;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import Flower_Shop.Repositories.BasketRepo;
import Flower_Shop.Repositories.ItemRepo;
import Flower_Shop.Repositories.OrderRepo;
import Flower_Shop.Repositories.ShopRepo;
import Flower_Shop.Repositories.StaffRepo;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	ItemRepo ir;
	
	@Autowired
	BasketRepo BR;
	
	@Autowired
	Statistic stat;
	
	@Autowired
	OrderRepo OR;
	
	@Autowired
	StaffRepo SR;
	

	@Autowired
	ShopRepo ShopR;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		
		//Initialise shop
		Shop s = ShopR.findOne(0);
		if(s == null)
			ShopR.save(new Shop(0, "default:name"));
		
		//Initialise a new basket
		Basket b = new Basket(1);
		
		//Initialise previous orders
		List<Order> orders = OR.findAll();
		for(Order x : orders){
			stat.addOrder(x);
		}
		//Initialise Administrator account
		Staff admin = SR.findOne(0);
		if(admin == null){
			SR.save(new Staff(0, "Admin", "Admin", "Administrator", "Password"));
		}
	}

}
