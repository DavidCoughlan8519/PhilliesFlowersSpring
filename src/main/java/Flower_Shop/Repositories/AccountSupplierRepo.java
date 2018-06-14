package Flower_Shop.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Flower_Shop.Supplier.RowMappers.AccountRowMapper;
import Flower_Shop.entities.Account;

@Repository
public class AccountSupplierRepo {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public AccountSupplierRepo(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Account getAccountById(int customerId){
		 sql = "SELECT * FROM ACCOUNT WHERE CUSTOMER_ID = ?";
		 return jdbcTemplate.queryForObject(sql, new Object[] {customerId}, new AccountRowMapper());
	}
	
	public void updateAddressById(String address,int customerId){
		sql = "UPDATE ACCOUNT SET ADDRESS = ? WHERE CUSTOMER_ID = ?";
		jdbcTemplate.queryForObject(sql, new Object[] {address,customerId}, new AccountRowMapper());
	}
	
	public void updateOutStandingBalance(int customerId,double outStandingBalance){
		 sql = "UPDATE ACCOUNT SET OUTSTANDING_BALANCE = ? WHERE CUSTOMER_ID = ?";
		 jdbcTemplate.queryForObject(sql, new Object[] {outStandingBalance,customerId}, new AccountRowMapper());
	}	
}
