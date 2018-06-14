package Flower_Shop.Supplier.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Flower_Shop.entities.Stock;

public class StockRowMapper implements RowMapper<Stock> {
	

	@Override
	public Stock mapRow(ResultSet rs, int index) throws SQLException {
		Stock stock = new Stock();
		stock.setItemId(rs.getInt("ID"));
		stock.setQuantity(rs.getInt("QUANTITY"));
		return stock;
	}

}
