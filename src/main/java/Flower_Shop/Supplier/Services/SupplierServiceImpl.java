package Flower_Shop.Supplier.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Flower_Shop.Repositories.AccountSupplierRepo;
import Flower_Shop.Repositories.ItemSupplierRepo;
import Flower_Shop.Repositories.StockSupplierRepo;
import Flower_Shop.entities.Account;
import Flower_Shop.entities.Item;


@Service
public class SupplierServiceImpl implements SupplierService{

	ItemSupplierRepo ir;
	AccountSupplierRepo ar;
	StockSupplierRepo sr;

	@Autowired
	public SupplierServiceImpl(ItemSupplierRepo ir, AccountSupplierRepo ar, StockSupplierRepo sr) {
	super();
	this.ir = ir;
	this.ar = ar;
	this.sr = sr;
    }


	@Override
	public Account getAccountById(int accountId) {
		return ar.getAccountById(accountId);
	}

	@Override
	public void updateAddressById(String address, int accountId) {
		ar.updateAddressById(address, accountId);
	}

	@Override
	public void updateOutStandingBalance(int accountId, double outStandingBalance) {
		ar.updateOutStandingBalance(accountId, outStandingBalance);
	}

	@Override
	public Item getItem(int item_id) {
		return ir.getItem(item_id);
	}

	@Override
	public int getItemStock(int item_id) {
		return sr.getItemStock(item_id);
	}

	@Override
	public void updateItemQuantity(int item_id, int amount) {
		sr.updateItemQuantity(item_id, amount);	
	}

	@Override
	public void sellItem(int item_id, int quantity, int accountId) {
		Item item = ir.getItem(item_id);
		//get the cost
		double cost = item.getCost() * quantity;
		Account account = ar.getAccountById(accountId);
		//update the balance
		double outStandingBalance = account.getOutStandingBalance()+ cost;
		ar.updateOutStandingBalance(accountId, outStandingBalance);
		//decrement the stock
		int stockAvailable = sr.getItemStock(item_id);
		sr.updateItemQuantity(item_id, stockAvailable - quantity);
	}


	@Override
	public List<Item> getItem() {
		return ir.getAllItems();
	}
}
