package Supplier;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Flower_Shop.Repositories.AccountSupplierRepo;
import Flower_Shop.entities.Account;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepoTest {

	
	@Autowired
	AccountSupplierRepo ar;
	
	@Test
	public void getAccountByAccountId() {
		Account acc = ar.getAccountById(1);
		assertTrue(acc.getAddress().equalsIgnoreCase("Main Street,Cork,Ireland"));	
	}
	
	@Test
	public void updateAddressById(){
		ar.updateAddressById("Changed", 1);
		Account acc = ar.getAccountById(1);
		assertTrue(acc.getAddress().equalsIgnoreCase("Changed"));
	}
	
	@Test
	public void updateOutStandingBalance(){
		 ar.updateOutStandingBalance(1, 300.00);
		 Account acc = ar.getAccountById(1);
		 assertTrue(acc.getOutStandingBalance() == 300.00);
	}	

}
