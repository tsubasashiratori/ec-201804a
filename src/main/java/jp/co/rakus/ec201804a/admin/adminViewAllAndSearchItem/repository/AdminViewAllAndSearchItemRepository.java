package jp.co.rakus.ec201804a.admin.adminViewAllAndSearchItem.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

@Repository
public class AdminViewAllAndSearchItemRepository {

	private static final String TABLE_NAME = "items";

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {

		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getInt("price"));
		item.setDescription(rs.getString("description"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	public List<Item> adminItemFindAll() {
		String sql = "SELECT id, name, description, price, imagepath, deleted FROM " + TABLE_NAME;

		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);

		return itemList;
	}

	public List<Item> adminItemFindByName(String name) {
		String sql = "SELECT id, name, description, price, deleted FROM " + TABLE_NAME + " WHERE name LIKE '%:name%'";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);

		List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);

		return itemList;
	}
}