package Supplier;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Flower_Shop.Repositories.ItemSupplierRepo;
import Flower_Shop.entities.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemRepoTests {

		
		@Autowired
		ItemSupplierRepo ir;
		
		@Test
		public void getItemById() {
			Item item = ir.getItem(1);
			assertTrue(item.getName().equalsIgnoreCase("Red Roses"));	
		}
}
