package Flower_Shop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Flower_Shop.Repositories.ItemRepo;
import Flower_Shop.entities.Item;

@Controller
public class AuthController {
	
	@Autowired
	ItemRepo IR;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/")
	public String enter(Model model){
		List<Item> items = IR.findAll();
		model.addAttribute("items", items);
		return "index";
	}
}
