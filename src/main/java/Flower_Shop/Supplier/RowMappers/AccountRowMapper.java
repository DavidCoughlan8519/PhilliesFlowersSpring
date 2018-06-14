package Flower_Shop.Supplier.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Flower_Shop.entities.Account;


public class AccountRowMapper implements RowMapper<Account> {
	

	@Override
	public Account mapRow(ResultSet rs, int index) throws SQLException {
		Account account = new Account();
		account.setCustomerId(rs.getInt("customerId"));
		account.setOutStandingBalance(rs.getDouble("outStandingBalance"));
		account.setAddress(rs.getString("address"));
		return account;
	}
}
