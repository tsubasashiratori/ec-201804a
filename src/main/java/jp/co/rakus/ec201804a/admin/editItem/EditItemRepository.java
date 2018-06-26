package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

@Repository
public class EditItemRepository {
	
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
	
	public Item loadByName(String name) {
		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME + " where name=:name order by id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		
		try {
			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
			return item;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
