package Flower_Shop.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Flower_Shop.Supplier.RowMappers.ItemRowMapper;
import Flower_Shop.entities.Item;

@Repository
public class ItemSupplierRepo {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public ItemSupplierRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Item getItem(int item_id) {
		sql = "SELECT * FROM ITEMS WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {item_id}, new ItemRowMapper());
	}
	
	public List<Item> getAllItems() {
		sql = "SELECT * FROM ITEMS";
		return jdbcTemplate.query(sql, new ItemRowMapper());
	}
}
