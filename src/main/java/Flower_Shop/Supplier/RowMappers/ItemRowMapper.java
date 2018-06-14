package Flower_Shop.Supplier.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Flower_Shop.entities.Item;

public class ItemRowMapper implements RowMapper<Item> {
	

	@Override
	public Item mapRow(ResultSet rs, int index) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("ID"));
		item.setName(rs.getString("NAME"));
		item.setImage(rs.getString("IMAGE"));
		item.setCost(rs.getInt("COST"));
		return item;
	}

}
