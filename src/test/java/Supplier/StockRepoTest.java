package Supplier;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Flower_Shop.Repositories.StockSupplierRepo;



@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class StockRepoTest {

	
	@Autowired
	StockSupplierRepo sr;
	
	@Test
	public void getItemStock(){
		int result = sr.getItemStock(1);
		assertTrue(result == 1000);
	}
	
	@Test
	public void updateItemQuantity(){
		int before = sr.getItemStock(1);
		sr.updateItemQuantity(1, 1000);
		int after = sr.getItemStock(1);
		assertTrue(after == before + 1000);
	}
}
