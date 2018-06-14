package Flower_Shop.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Flower_Shop.Repositories.BasketRepo;
import Flower_Shop.Repositories.ItemRepo;
import Flower_Shop.Repositories.OrderRepo;
import Flower_Shop.entities.Address;
import Flower_Shop.entities.Basket;
import Flower_Shop.entities.Basket_Item;
import Flower_Shop.entities.Item;
import Flower_Shop.entities.Order;
import Flower_Shop.entities.Statistic;

@Controller
@RequestMapping("/shop")
public class Controllers {
	
	@Autowired
	ItemRepo IR;
	
	@Autowired
	BasketRepo BR;
	
	@Autowired
	OrderRepo OR;
	
	@Autowired
	Statistic stat;
	
	private static final String OPERATION = "operation";
	private static final String COUNTER = "count";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String ADDRESS = "address";
	private static final String TOWN = "town";
	private static final String CITY = "city";
	private static final String POSTCODE = "post";
	
	@GetMapping("/")
	public String home_page(Model model)
	{
		List<Item> items = IR.findAll();
		model.addAttribute("items", items);
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{itemID}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Item> showItems(@PathVariable int itemID)
	{
		Item item = (Item)IR.findOne(itemID);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart/{cartID}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Basket> showCart(@PathVariable int cartID)
	{
		Basket basket = BR.findOne(cartID);
		return new ResponseEntity<Basket>(basket, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cart/{cartID}/{itemID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> editCart(@PathVariable int cartID, @PathVariable int itemID, @RequestBody Map<String, String> body)
	{
		String operation = get_operation(body);
		String count = get_count(body);
		Basket basket = BR.findOne(cartID);
		if(basket == null)
			basket = new Basket(1);
		Item item = (Item)IR.findOne(itemID);
		if(operation.equals("ADD")){
			basket.add(item, Integer.parseInt(count));
		}else if(operation.equals("REMOVE")){
			basket.remove(item);
		}
		BR.save(basket);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/order/buy/{cartID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> buyOrder(@PathVariable int cartID, @RequestBody Map<String, String> body){
		Basket basket = BR.findOne(cartID);
		Map<String, String> info = get_Shipping(body);
		if(info.size() != 6)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);	
		Address address = new Address(info.get(FIRSTNAME), info.get(LASTNAME), info.get(ADDRESS), info.get(TOWN), info.get(CITY), info.get(POSTCODE));
		Order order = new Order(OR.count(), basket.getCost(), basket.getItems(), address);
		OR.save(order);
		stat.addOrder(order);
		List<Basket_Item> list = new ArrayList<>(basket.getItems().values());
		for (Basket_Item basket_item : list) {
		    basket.remove(basket_item.getItem());
		}
		BR.save(basket);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/order/view/{orderID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Order> viewOrder(@PathVariable long orderID){
		Order order = OR.findOne((int) orderID);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	private Map<String, String> get_Shipping(Map<String, String> body){
		Map<String, String> list = new HashMap<String, String>();
		if(body.containsKey(FIRSTNAME)){
			list.put(FIRSTNAME, body.get(FIRSTNAME));
		}
		if(body.containsKey(LASTNAME)){
			list.put(LASTNAME, body.get(LASTNAME));
		}
		if(body.containsKey(ADDRESS)){
			list.put(ADDRESS, body.get(ADDRESS));
		}
		if(body.containsKey(TOWN)){
			list.put(TOWN, body.get(TOWN));
		}
		if(body.containsKey(CITY)){
			list.put(CITY, body.get(CITY));
		}
		if(body.containsKey(POSTCODE)){
			list.put(POSTCODE, body.get(POSTCODE));
		}
		return list;
	}
	
	private String get_operation(Map<String, String> body){
		String value = null;
		if(body.containsKey(OPERATION)){
			value = body.get(OPERATION);
		}
		return value;
	}
	
	private String get_count(Map<String, String> body){
		String value = null;
		if(body.containsKey(COUNTER)){
			value = body.get(COUNTER);
		}
		return value;
	}
	
}
