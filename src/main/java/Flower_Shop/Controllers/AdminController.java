package Flower_Shop.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import Flower_Shop.Repositories.ShopRepo;
import Flower_Shop.Repositories.StaffRepo;
import Flower_Shop.Supplier.Services.SupplierServiceImpl;
import Flower_Shop.entities.Basket;
import Flower_Shop.entities.Basket_Item;
import Flower_Shop.entities.Item;
import Flower_Shop.entities.Order;
import Flower_Shop.entities.Shop;
import Flower_Shop.entities.Staff;
import Flower_Shop.entities.Statistic;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final String POSITION = "position";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String PASSWORD = "password";
	
	@Autowired
	ItemRepo IR;
	
	@Autowired
	BasketRepo BR;
	
	@Autowired
	Statistic stats;
	
	@Autowired
	OrderRepo orders;
	
	@Autowired
	StaffRepo SR;
	
	@Autowired
	ShopRepo ShopR;
	
	@Autowired
	SupplierServiceImpl ssi;
	
	@GetMapping("/statistic")
	public String statistics_page(Model model)
	{
		List<Staff> staff = SR.findAll();
		List<Item> items = IR.findAll();
		List<Item> supplierItems = ssi.getItem();
		class innerItem{
			public int i;
			public Item item;
			public innerItem(int i, Item item){this.i = i; this.item=item;}
		} 
		Map<String, innerItem> map = new HashMap<String, innerItem>();
		for(Item i : supplierItems){
			int s = ssi.getItemStock(i.getId());
			map.put(String.valueOf(i.getId()), new innerItem(s, i));
		}
		List<Integer> range = IntStream.range(0, 50).boxed().collect(Collectors.toList());
		model.addAttribute("nummbers", range);
		model.addAttribute("Statistics", stats);
		model.addAttribute("items", items);
		model.addAttribute("Staff", staff);
		model.addAttribute("supply", map);
		return "adminPanel";
	}	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/staff/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> EditStaff(@RequestBody Map<String, String> body){
		Staff staff = SR.findOne(Integer.parseInt(body.get("id")));
		staff.setPosition(body.get(POSITION));
		SR.delete(Integer.parseInt(body.get("id")));
		SR.save(staff);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/staff/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> CreateStaff(@RequestBody Map<String, String> body){
		SR.save(new Staff((int)SR.count(), body.get(FIRSTNAME), body.get(LASTNAME), body.get(POSITION), body.get(PASSWORD) ));
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/staff/get", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Staff>> viewOrder(){
		List<Staff> staff = SR.findAll();
		return new ResponseEntity<List<Staff>>(staff, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/shop/getName", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> getShopName(){
		Shop s = ShopR.findOne(0);
		return new ResponseEntity<String>(s.getName(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Order> getOrder(@PathVariable int orderId){
		Order order = orders.findOne(orderId);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/shop/setName", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> setShopName(@RequestBody Map<String, String> body){
		Shop s = ShopR.findOne(0);
		s.setName(body.get("shopName"));
		ShopR.delete(0);
		ShopR.save(s);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cart/buy/{cartID}")
	ResponseEntity<Void> createSupplierOrder(@PathVariable int cartID){
		Basket b = BR.findOne(cartID);
		Map<String, Basket_Item> items =  b.getItems();
		int amount = 0;
		for (String key : items.keySet()) {
			Item item = items.get(key).getItem();
			amount = items.get(key).getCount();
			ssi.updateItemQuantity(item.getId(), ssi.getItemStock(item.getId())-amount);
			IR.save(item);
		}
		stats.setOutStandingAmount(stats.getOutStandingAmount()+b.getCost());
		b.setItems(new HashMap<String, Basket_Item>());
		b.setCost(0);
		BR.save(b);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cart/{cartID}/{itemID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> addCart(@PathVariable int cartID, @PathVariable int itemID, @RequestBody Map<String, String> body)
	{
		String operation = body.get("operation");
		String count = body.get("count");
		Basket basket = BR.findOne(cartID);
		if(basket == null)
			basket = new Basket(cartID);
		Item item = ssi.getItem(itemID);
		if(operation.equals("ADD")){
			basket.add(item, Integer.parseInt(count));
		}else if(operation.equals("REMOVE")){
			basket.remove(item);
		}
		BR.save(basket);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

}
