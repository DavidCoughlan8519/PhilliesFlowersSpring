package Flower_Shop.Supplier.Services;

import java.util.List;

import Flower_Shop.entities.Account;
import Flower_Shop.entities.Item;

public interface SupplierService {
	
	//-----------Account
	public Account getAccountById(int accountId);

	public void updateAddressById(String address,int accountId);

	public void updateOutStandingBalance(int accountId,double outStandingBalance);

	//---------- Item
	public Item getItem(int item_id);
	
	public List<Item> getItem();

	//---------- Stock
	public int getItemStock(int item_id);

	public void updateItemQuantity(int item_id, int amount);
	
	public void sellItem(int item_id,int quantity,int accountId);
}
