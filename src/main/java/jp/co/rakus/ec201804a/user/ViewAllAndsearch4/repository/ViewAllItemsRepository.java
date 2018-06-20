package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

@Repository
public class ViewAllItemsRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_NAME = "items";

	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getInt("price"));
		item.setImagePath(rs.getString("imagePath"));
		item.setDeleted(rs.getBoolean("deleted"));

		return item;
	};

	public List<Item> findAllNotDeleted() {

		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME
				+ " where deleted = false order by price";

		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);

		return itemList;
	}

	public List<Item> findByNameNotDeleted(String name) {

		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME
				+ " where deleted = false and name LIKE :name order by price";

		if (name == "") {
			List<Item> nullItemList = new ArrayList<>();
			return nullItemList;

		} else {
			String nameFixed = "%" + name + "%";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", nameFixed);
			List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
			return itemList;
		}
	}
	
	public Item findDetailByIdNotDeleted(long id) {
		
		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME + " where id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
		
		return item;
	}

}
